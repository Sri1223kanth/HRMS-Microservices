package com.Workday.Service;

import com.Workday.Entity.Employee;
import com.Workday.Kafka.EmployeeProducer;
import com.Workday.Repsoitory.EmployeeRepository;
import com.Workday.dto.EmployeeCreatedEvent;
import com.Workday.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeProducer employeeProducer;


    public Employee createEmployee(Employee employee) {
       Employee savedEmployee =  employeeRepository.save(employee);

        EmployeeCreatedEvent event = new EmployeeCreatedEvent(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getEmail(),
                savedEmployee.getPhoneNumber(),
                savedEmployee.getJobTitle(),
                savedEmployee.getDateOfJoining(),
                savedEmployee.getStatus()

        );

        employeeProducer.publishEmployee(event);

        return savedEmployee;

    }

    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
//                employee.getManager(),
//                employee.getDepartment(),
                employee.getStatus(),
                employee.getDateOfJoining(),
                employee.getJobTitle()
        );
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }

    public Employee updateEmployee(Long id, Employee employee) {

        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not Found"));

        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());

        return employeeRepository.save(employee1);
    }

    public void deleteEmployee(Long id) {
         employeeRepository.deleteById(id);
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public EmployeeDto getEmployeeByEmail(String email) {

        Employee employee = (Employee) employeeRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Employee not found By Email : " + email));

        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
//                employee.getManager(),
//                employee.getDepartment(),
                employee.getStatus(),
                employee.getDateOfJoining(),
                employee.getJobTitle()
        );
    }

}
