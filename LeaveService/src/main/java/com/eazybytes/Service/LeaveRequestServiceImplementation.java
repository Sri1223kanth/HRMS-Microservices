package com.eazybytes.Service;

import com.eazybytes.Client.EmployeeClient;
import com.eazybytes.Dto.LeaveRequestDto;
import com.eazybytes.Dto.LeaveTypeDto;
import com.eazybytes.Entity.LeaveRequest;
import com.eazybytes.Repository.LeaveRequestRepository;
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
public class LeaveRequestServiceImplementation implements LeaveRequestService {

    private final LeaveRequestRepository repository;

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
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto dto) {

        // Verify Employee exists
        employeeClient.getEmployeeById(dto.getEmployeeId());

        LeaveRequest entity = new LeaveRequest();

        BeanUtils.copyProperties(dto, entity);

        LeaveRequest saved = repository.save(entity);

        LeaveRequestDto response = new LeaveRequestDto();

        BeanUtils.copyProperties(saved, response);

        return response;
    }

    @Override
    public List<LeaveRequestDto> getAllLeaveRequests() {

        return repository.findAll()
                .stream()
                .map(request -> {
                    LeaveRequestDto dto = new LeaveRequestDto();
                    BeanUtils.copyProperties(request, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LeaveRequestDto getLeaveRequestById(Long id) {

        LeaveRequest entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave Request Not Found"));

        LeaveRequestDto dto = new LeaveRequestDto();

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    @Override
    public LeaveRequestDto updateLeaveRequest(Long id, LeaveRequestDto dto) {

        // Verify Employee exists
        employeeClient.getEmployeeById(dto.getEmployeeId());

        LeaveRequest entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave Request Not Found"));

        BeanUtils.copyProperties(dto, entity);

        entity.setId(id);

        LeaveRequest updated = repository.save(entity);

        LeaveRequestDto response = new LeaveRequestDto();

        BeanUtils.copyProperties(updated, response);

        return response;
    }

    @Override
    public void deleteLeaveRequest(Long id) {

        repository.deleteById(id);
    }

    public LeaveRequestDto employeeServiceFallback(LeaveTypeDto dto, Exception e){
        throw new RuntimeException("Employee Service is unavailable");
    }
}