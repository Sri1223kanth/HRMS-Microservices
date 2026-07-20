package com.eazybytes.Exception;

import com.eazybytes.Dto.SalaryStructureDto;
import com.eazybytes.Entity.SalaryStructure;
import org.springframework.http.ResponseEntity;

public class SalaryStructureNotFoundException extends RuntimeException {

    public SalaryStructureNotFoundException(Long id){
        super("Salary Structure Not Found for Id :" + id);
    }
}
