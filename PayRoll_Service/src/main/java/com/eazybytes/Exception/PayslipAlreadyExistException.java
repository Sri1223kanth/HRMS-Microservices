package com.eazybytes.Exception;

public class PayslipAlreadyExistException extends RuntimeException{

    public PayslipAlreadyExistException(Long employeeId,
                                        Integer month,
                                        Integer year) {

        super("PayslipAlreadyExistsException already exists for Employee Id : "
                + employeeId
                + " Month : "
                + month
                + " Year : "
                + year);
    }
}
