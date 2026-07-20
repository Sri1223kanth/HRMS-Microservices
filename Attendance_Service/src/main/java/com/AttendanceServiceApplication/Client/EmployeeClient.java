package com.AttendanceServiceApplication.Client;


import com.AttendanceServiceApplication.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "employee-service",
        url ="http://localhost:8080"
)
public interface EmployeeClient {

    @GetMapping("/api/employees/{id}")
    EmployeeDto getEmployeeById(
            @PathVariable Long id
    );


}
