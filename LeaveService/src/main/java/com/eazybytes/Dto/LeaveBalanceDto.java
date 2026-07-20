package com.eazybytes.Dto;

import lombok.Data;

import java.time.Year;

@Data
public class LeaveBalanceDto {


    private Long id;

    private Long employeeId;

    private Integer remainingLeaves;

    private Integer totalLeaves;

    private Integer usedLeaves;

    private Year year;
}
