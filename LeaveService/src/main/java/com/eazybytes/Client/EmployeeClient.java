package com.eazybytes.Client;

import com.eazybytes.Dto.EmployeeDto;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping("/api/employees/{id}")
    EmployeeDto getEmployeeById(@PathVariable Long id);
}
