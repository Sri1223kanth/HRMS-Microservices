package com.eazybytes.Service;

import com.eazybytes.Client.EmployeeClient;
import com.eazybytes.Dto.LeaveBalanceDto;
import com.eazybytes.Entity.Leavebalance;
import com.eazybytes.Repository.LeaveBalanceRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveBalanceServiceImplementation implements LeaveBalanceService{

    private final LeaveBalanceRepository repository;

    private final EmployeeClient employeeClient;


    @RateLimiter(
            name = "employeeService",
            fallbackMethod = "employeeServiceFallback")
    @Retry(
            name = "employeeService",
            fallbackMethod = "employeeServiceFallback")
    @CircuitBreaker(
            name = "employeeService",
            fallbackMethod = "employeeServiceFallback"
    )
    @Override
    public LeaveBalanceDto createLeaveBalance(LeaveBalanceDto dto) {

        // Verify Employee exists
        employeeClient.getEmployeeById(dto.getEmployeeId());

        Leavebalance entity = new Leavebalance();

        BeanUtils.copyProperties(dto, entity);

        Leavebalance saved = repository.save(entity);

        LeaveBalanceDto response = new LeaveBalanceDto();

        BeanUtils.copyProperties(saved, response);

        return response;
    }

    @Override
    public List<LeaveBalanceDto> getAllLeaveBalances() {

        return repository.findAll()
                .stream()
                .map(balance -> {
                    LeaveBalanceDto dto = new LeaveBalanceDto();
                    BeanUtils.copyProperties(balance, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LeaveBalanceDto getLeaveBalanceById(Long id) {

        Leavebalance entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave Balance Not Found"));

        LeaveBalanceDto dto = new LeaveBalanceDto();

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    @Override
    public LeaveBalanceDto updateLeaveBalance(Long id, LeaveBalanceDto dto) {

        Leavebalance entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave Balance Not Found"));

        BeanUtils.copyProperties(dto, entity);

        entity.setId(id);

        Leavebalance updated = repository.save(entity);

        LeaveBalanceDto response = new LeaveBalanceDto();

        BeanUtils.copyProperties(updated, response);

        return response;
    }

    @Override
    public void deleteLeaveBalance(Long id) {

        repository.deleteById(id);
    }

    public LeaveBalanceDto employeeServiceFallback(
            LeaveBalanceDto dto,
            Exception ex) {

        throw new RuntimeException("Employee Service is unavailable");
    }
}
