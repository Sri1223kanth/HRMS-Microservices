package com.Workday.Controller;


import com.Workday.Entity.Employee;
import com.Workday.Repsoitory.EmployeeRepository;
import com.Workday.Service.EmployeeService;
import com.Workday.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@Tag(
        name = "Employee Management",
        description = "APIs for managing employee information"
)
public class EmployeeController {


    private final EmployeeService employeeService;

    @Operation(
            summary = "Create Employee",
            description = "Creates a new employee"
    )
    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){
     return ResponseEntity.status(HttpStatus.CREATED)
             .body(employeeService.createEmployee(employee));
    }

    @Operation(
            summary = "Get Employee by ID",
            description = "Returns employee details using employee ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable Long id) {

       return ResponseEntity.ok( employeeService.getEmployeeById(id));
    }

//    @GetMapping
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(employeeService.getAllEmployees());
//    }

    @Operation(
            summary = "Update Employee",
            description = "Updates employee information"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,@Valid
            @RequestBody Employee employee) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, employee));
    }

    @Operation(
            summary = "Delete Employee",
            description = "Deletes an employee by ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get All Employees through Pageable",
            description = "Get All Employees through Pageable Object"
    )
    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(Pageable pageable) {

        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    @Operation(
            summary = "Get Employee By Email",
            description = "Return Employee details Using Email"
    )
    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String email){
        return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
    }

}
