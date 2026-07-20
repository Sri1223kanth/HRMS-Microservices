package com.eazybytes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SalaryStructureNotFoundException.class)
    public ResponseEntity<String> handleSalaryStructureNotFoundException(
            SalaryStructureNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(PayslipAlreadyExistException.class)
    public ResponseEntity<String> handlePayslipAlreadyExists(
            PayslipAlreadyExistException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
