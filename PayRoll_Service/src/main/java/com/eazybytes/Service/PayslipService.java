package com.eazybytes.Service;

import com.eazybytes.Dto.PayslipDto;
import com.eazybytes.Entity.Payslip;
import com.eazybytes.Entity.SalaryStructure;
import com.eazybytes.Exception.PayslipAlreadyExistException;
import com.eazybytes.Repsoitory.PayslipRepository;
import com.eazybytes.Repsoitory.SalaryStructureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayslipService {

    private final PayslipRepository payslipRepository;

    private final SalaryStructureRepository salaryStructureRepository;

    private PayslipDto convertToDto(Payslip payslip) {

        PayslipDto dto = new PayslipDto();

        dto.setId(payslip.getId());
        dto.setEmployeeId(payslip.getEmployeeId());
        dto.setMonth(payslip.getMonth());
        dto.setYear(payslip.getYear());
        dto.setTotalAmount(payslip.getTotalAmount());
        dto.setDate(payslip.getDate());

        return dto;
    }

    private Payslip convertToEntity(PayslipDto dto) {

        Payslip payslip = new Payslip();

        payslip.setEmployeeId(dto.getEmployeeId());
        payslip.setMonth(dto.getMonth());
        payslip.setYear(dto.getYear());
        payslip.setTotalAmount(dto.getTotalAmount());
        payslip.setDate(dto.getDate());

        return payslip;
    }

//    public PayslipDto createPayslip(PayslipDto dto) {
//
//        Payslip payslip = convertToEntity(dto);
//
//        Payslip saved = payslipRepository.save(payslip);
//
//        return convertToDto(saved);
//    }

    public List<PayslipDto> getAllPayslips() {

        return payslipRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PayslipDto getPayslipById(Long id) {

        Payslip payslip = payslipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payslip Not Found"));

        return convertToDto(payslip);
    }

    public PayslipDto updatePayslip(Long id, PayslipDto dto) {

        Payslip payslip = payslipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payslip Not Found"));

        payslip.setEmployeeId(dto.getEmployeeId());
        payslip.setMonth(dto.getMonth());
        payslip.setYear(dto.getYear());
        payslip.setTotalAmount(dto.getTotalAmount());
        payslip.setDate(dto.getDate());

        Payslip updated = payslipRepository.save(payslip);

        return convertToDto(updated);
    }

    public void deletePayslip(Long id) {

        Payslip payslip = payslipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payslip Not Found"));

        payslipRepository.delete(payslip);
    }

    public PayslipDto generatePayslip(long employeeId) {
        SalaryStructure salary = salaryStructureRepository
                .findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Salary Structure Not Found"));


        double grossSalary = salary.getBasicPay()
                + salary.getSpecialAllowance()
                +salary.getHra();

        double netSalary = salary.getPfDeduction()
                -salary.getProfessionalTax();

//        ??create PaySlip

        Payslip payslip = new Payslip();
        payslip.setEmployeeId(employeeId);
        payslip.setDate(LocalDateTime.now());
        payslip.setYear(LocalDateTime.now().getYear());
        payslip.setMonth(LocalDateTime.now().getMonthValue());
        payslip.setTotalAmount(netSalary);

        Payslip saved = payslipRepository.save(payslip);

        return convertToDto(saved);
    }

    public List<PayslipDto> getPayslipsByEmployee(Long employeeId) {

        List<Payslip> payslips =
                payslipRepository.findByEmployeeId(employeeId);

        return payslips.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PayslipDto createPayslip(PayslipDto dto) {

        payslipRepository
                .findByEmployeeIdAndMonthAndYear(
                        dto.getEmployeeId(),
                        dto.getMonth(),
                        dto.getYear())
                .ifPresent(payslip -> {
                    throw new PayslipAlreadyExistException(
                            dto.getEmployeeId(),
                            dto.getMonth(),
                            dto.getYear());
                });

        Payslip payslip = convertToEntity(dto);

        Payslip saved = payslipRepository.save(payslip);

        return convertToDto(saved);
    }
}
