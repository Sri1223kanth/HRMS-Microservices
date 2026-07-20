package com.eazybytes.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryStructureDto {

    private Long id;

    private  Long employeeId;

    private double basicPay;

    private double hra;

    private double specialAllowance;

    private double pfDeduction;

    private double professionalTax;

    private LocalDate effectiveFrom;
}
