package com.AttendanceServiceApplication.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class AttendanceRequestDto {


    private Long employeeId;

    private LocalTime check_in;

    private LocalTime check_out;

    private String status;
}


