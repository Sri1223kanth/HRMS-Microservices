package com.AttendanceServiceApplication.Controller;

import com.AttendanceServiceApplication.Entity.Attendance;
import com.AttendanceServiceApplication.Service.AttendanceService;
import com.AttendanceServiceApplication.dto.AttendanceRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {


    private final AttendanceService attendanceService;

    @Operation(summary = "Mark Attendance", description = "Marks attendance for an employee.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Attendance marked successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid attendance request"),
            @ApiResponse(responseCode = "409", description = "Attendance already marked"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/checkin")
    public String Checkin (@RequestBody AttendanceRequestDto requestDto){
        return attendanceService.markAttendance(requestDto);
    }

    @Operation(summary = "Mark Attendance", description = "Marks attendance for an employee.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Attendance marked successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid attendance request"),
            @ApiResponse(responseCode = "409", description = "Attendance already marked"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/check-out")
    public String checkOut(@RequestBody AttendanceRequestDto requestDto){
        return attendanceService.checkOut(requestDto);
    }

    @GetMapping("/employee/{employeeId}/date/{date}")
    public ResponseEntity<Attendance> getAttendanceByEmployeeAndDate(
            @PathVariable Long employeeId,
            @PathVariable LocalDate date) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByEmployeeAndDate(
                        employeeId,
                        date));
    }

    @Operation(summary = "Get All Attendance", description = "Retrieves all attendance records.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Attendance records retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No attendance records found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<Page<Attendance>> getAllAttendance(
            Pageable pageable) {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance(pageable));
    }



}
