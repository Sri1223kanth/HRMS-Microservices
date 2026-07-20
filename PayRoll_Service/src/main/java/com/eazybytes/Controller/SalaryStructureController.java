package com.eazybytes.Controller;

import com.eazybytes.Dto.SalaryStructureDto;
import com.eazybytes.Entity.SalaryStructure;
import com.eazybytes.Service.SalaryStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salary-structure")
public class SalaryStructureController {


    private final SalaryStructureService salaryStructureService;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<SalaryStructure> getSalaryStructureByEmployeeId(@PathVariable Long employeeId){
        SalaryStructure structure = salaryStructureService.getSalaryStructureByEmployeeId(employeeId);
        return ResponseEntity.ok(structure);
    }

    @PostMapping
    public ResponseEntity<SalaryStructureDto> createSalaryStructure(@RequestBody SalaryStructureDto salaryStructureDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salaryStructureService.createSalaryStructure(salaryStructureDto));
    }

    @GetMapping
    public ResponseEntity<List<SalaryStructureDto>> getAllSalaryStructureById(){
        return ResponseEntity.ok(salaryStructureService.getAllSalaryStructure());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryStructureDto> getSalaryStructure(@PathVariable Long id){
        return ResponseEntity.ok(salaryStructureService.getSalaryStructureById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaryStructureDto> updateSalaryStructure(@PathVariable Long id,@RequestBody SalaryStructureDto salaryStructureDto){
        return ResponseEntity.ok(salaryStructureService.updateSalaryStructure(id,salaryStructureDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalaryStructure(
            @PathVariable Long id) {

        salaryStructureService
                .deleteSalaryStructure(id);

        return ResponseEntity.noContent().build();
    }



}
