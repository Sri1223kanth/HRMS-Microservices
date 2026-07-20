package com.eazybytes.Exception;


import com.eazybytes.Dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(LeaveTypeNotFoundException.class)
//    public ResponseEntity<String> handleLeaveTypeNotFound(LeaveTypeNotFoundException exception){
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(exception.getMessage());
//    }

    @ExceptionHandler(LeaveTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLeaveTypeNotFound(
            LeaveTypeNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Not Found");
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}
