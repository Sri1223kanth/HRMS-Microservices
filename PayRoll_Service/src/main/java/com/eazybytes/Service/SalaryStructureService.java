package com.eazybytes.Service;

import com.eazybytes.Client.EmployeeClient;
import com.eazybytes.Dto.SalaryStructureDto;
import com.eazybytes.Entity.SalaryStructure;
import com.eazybytes.Exception.SalaryStructureNotFoundException;
import com.eazybytes.Repsoitory.SalaryStructureRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.spring6.fallback.FallbackMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryStructureService {

    private final SalaryStructureRepository salaryStructureRepository;

    private final EmployeeClient employeeClient;

    private SalaryStructureDto convertToDto(
            SalaryStructure salaryStructure) {

        SalaryStructureDto dto = new SalaryStructureDto();

        dto.setId(salaryStructure.getId());
        dto.setEmployeeId(salaryStructure.getEmployeeId());
        dto.setBasicPay(salaryStructure.getBasicPay());
        dto.setHra(salaryStructure.getHra());
        dto.setSpecialAllowance(
                salaryStructure.getSpecialAllowance());
        dto.setPfDeduction(
                salaryStructure.getPfDeduction());
        dto.setProfessionalTax(
                salaryStructure.getProfessionalTax());
        dto.setEffectiveFrom(
                salaryStructure.getEffectiveFrom());

        return dto;
    }

    private SalaryStructure convertToEntity(
            SalaryStructureDto dto) {

        SalaryStructure salaryStructure =
                new SalaryStructure();

        salaryStructure.setEmployeeId(dto.getEmployeeId());
        salaryStructure.setBasicPay(dto.getBasicPay());
        salaryStructure.setHra(dto.getHra());
        salaryStructure.setSpecialAllowance(
                dto.getSpecialAllowance());
        salaryStructure.setPfDeduction(
                dto.getPfDeduction());
        salaryStructure.setProfessionalTax(
                dto.getProfessionalTax());
        salaryStructure.setEffectiveFrom(
                dto.getEffectiveFrom());

        return salaryStructure;
    }


    public SalaryStructure getSalaryStructureByEmployeeId(Long employeeId) {
        return (SalaryStructure) salaryStructureRepository.findByEmployeeId(employeeId)
                .orElseThrow(()-> new RuntimeException("Salary Structure  not configured for employee id : " + employeeId));
    }


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
    public SalaryStructureDto createSalaryStructure(
            SalaryStructureDto salaryStructureDto) {

        // Check if Employee ID is present
        if (salaryStructureDto.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee Id cannot be null");
        }

        // Verify Employee exists by calling Employee Service
        employeeClient.getEmployeeById(salaryStructureDto.getEmployeeId());

        // Check duplicate Salary Structure
        if (salaryStructureRepository.existsByEmployeeId(
                salaryStructureDto.getEmployeeId())) {

            throw new RuntimeException(
                    "Salary Structure already exists for Employee Id : "
                            + salaryStructureDto.getEmployeeId());
        }

        // Validate Salary Details
        validateSalaryStructure(salaryStructureDto);

        // Convert DTO to Entity
        SalaryStructure salaryStructure = convertToEntity(salaryStructureDto);

        // Save
        SalaryStructure savedSalaryStructure =
                salaryStructureRepository.save(salaryStructure);

        // Convert Entity to DTO
        return convertToDto(savedSalaryStructure);
    }

    private void validateSalaryStructure(
            SalaryStructureDto dto) {

        if (dto.getBasicPay() < 0) {
            throw new IllegalArgumentException(
                    "Basic Pay cannot be negative");
        }

        if (dto.getHra() < 0) {
            throw new IllegalArgumentException(
                    "HRA cannot be negative");
        }

        if (dto.getSpecialAllowance() < 0) {
            throw new IllegalArgumentException(
                    "Special Allowance cannot be negative");
        }

        if (dto.getPfDeduction() < 0) {
            throw new IllegalArgumentException(
                    "PF Deduction cannot be negative");
        }

        if (dto.getProfessionalTax() < 0) {
            throw new IllegalArgumentException(
                    "Professional Tax cannot be negative");
        }

        if (dto.getEffectiveFrom() == null) {
            throw new IllegalArgumentException(
                    "Effective Date cannot be null");
        }
    }

    public List<SalaryStructureDto> getAllSalaryStructure() {

        List<SalaryStructure> salaryStructures =
                salaryStructureRepository.findAll();

        return salaryStructures.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SalaryStructureDto getSalaryStructureById(Long id) {

        SalaryStructure salaryStructure =
                salaryStructureRepository.findById(id)
                        .orElseThrow(() ->
                                new SalaryStructureNotFoundException(id));

        return convertToDto(salaryStructure);
    }
    public SalaryStructureDto updateSalaryStructure(
            Long id,
            SalaryStructureDto salaryStructureDto) {

        SalaryStructure salaryStructure =
                salaryStructureRepository.findById(id)
                        .orElseThrow(() ->
                                new SalaryStructureNotFoundException(id));

        salaryStructure.setBasicPay(
                salaryStructureDto.getBasicPay());

        salaryStructure.setHra(
                salaryStructureDto.getHra());

        salaryStructure.setSpecialAllowance(
                salaryStructureDto.getSpecialAllowance());

        salaryStructure.setPfDeduction(
                salaryStructureDto.getPfDeduction());

        salaryStructure.setProfessionalTax(
                salaryStructureDto.getProfessionalTax());

        salaryStructure.setEffectiveFrom(
                salaryStructureDto.getEffectiveFrom());

        SalaryStructure updatedSalaryStructure =
                salaryStructureRepository.save(salaryStructure);

        return convertToDto(updatedSalaryStructure);
    }

    public void deleteSalaryStructure(Long id) {

        SalaryStructure salaryStructure =
                salaryStructureRepository.findById(id)
                        .orElseThrow(() ->
                                new SalaryStructureNotFoundException(id));

        salaryStructureRepository.delete(salaryStructure);
    }

    public SalaryStructureDto employeeServiceFallback(SalaryStructureDto dto,Exception ex){
        throw  new RuntimeException(
                "Employee Service is Currently Unavailable . Please try after sometime !!"
        );
    }
}
