package com.Workday.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreatedEvent {

    private Long employeeId;
    private String name;
    private String email;
    private String phoneNumber;
    private String jobTitle;
//    private String departmentName;
    private LocalDate dateOfJoining;
    private String status;
}
