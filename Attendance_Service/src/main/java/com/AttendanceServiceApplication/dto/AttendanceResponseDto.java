package com.AttendanceServiceApplication.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class AttendanceResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String employeeName;

    private LocalDate attendanceDate;

    private String status;
}
