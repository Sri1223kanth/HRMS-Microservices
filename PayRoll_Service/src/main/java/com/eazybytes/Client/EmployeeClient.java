package com.eazybytes.Client;

import com.eazybytes.Dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping("/api/employee/{id}")
    EmployeeDto getEmployeeById(@PathVariable Long id);
}
