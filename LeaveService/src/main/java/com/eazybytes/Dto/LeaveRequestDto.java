package com.eazybytes.Dto;

import com.eazybytes.Entity.LeaveRequestStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDto {

    private  Long id;

    private Long employeeId;

    private String leaveType;

    private LeaveRequestStatus status;

    private String approvedBy;

    private Integer numberOfDays;
}
