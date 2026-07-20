package com.eazybytes.Controller;

import com.eazybytes.Dto.PayslipDto;
import com.eazybytes.Service.PayslipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payslips")
public class PayslipController {

    private final PayslipService payslipService;

    @PostMapping
    public ResponseEntity<PayslipDto> createPayslip(
            @RequestBody PayslipDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(payslipService.createPayslip(dto));
    }

    @GetMapping
    public ResponseEntity<List<PayslipDto>> getAllPayslips() {

        return ResponseEntity.ok(
                payslipService.getAllPayslips());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayslipDto> getPayslipById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                payslipService.getPayslipById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PayslipDto> updatePayslip(
            @PathVariable Long id,
            @RequestBody PayslipDto dto) {

        return ResponseEntity.ok(
                payslipService.updatePayslip(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayslip(
            @PathVariable Long id) {

        payslipService.deletePayslip(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generate/{employeeId}")
    public ResponseEntity<PayslipDto> generatePayslip(@PathVariable long employeeId){
        return ResponseEntity.ok(payslipService.generatePayslip(employeeId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayslipDto>> getPayslipsByEmployee(
            @PathVariable Long employeeId){

        return ResponseEntity.ok(
                payslipService.getPayslipsByEmployee(employeeId));
    }
}
