package com.eazybytes.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayslipDto {

    private Long id;

    private Long employeeId;

    private Integer month;

    private Integer year;

    private double totalAmount;

    private LocalDateTime date;
}
