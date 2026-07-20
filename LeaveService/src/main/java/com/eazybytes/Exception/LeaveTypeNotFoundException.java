package com.eazybytes.Exception;

public class LeaveTypeNotFoundException extends RuntimeException{

    public LeaveTypeNotFoundException(Long id){
        super("Leave Type Not Found  :" + id);
    }
}
