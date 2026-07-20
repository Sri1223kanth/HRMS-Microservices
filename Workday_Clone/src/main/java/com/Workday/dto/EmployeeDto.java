package com.Workday.dto;

import com.Workday.Entity.Department;
import com.Workday.Entity.Employee;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {


    private Long id;

    @NotBlank(message = " Employee name is required")
    @Size(min = 3,max = 50, message = " Name  must be  between  3 and 60  characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email formate")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must contains 10 digits"
    )
    private String phoneNumber;

//    private Long managerId;

//    private Long departmentId;

    private String status;

    private LocalDate dateOfJoining;

    @NotBlank(message = "JobTitle is Required")
    private String jobTitle;

}
