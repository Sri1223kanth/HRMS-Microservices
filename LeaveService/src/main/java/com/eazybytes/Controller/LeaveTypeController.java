package com.eazybytes.Controller;

import com.eazybytes.Dto.LeaveTypeDto;
import com.eazybytes.Entity.LeaveType;
import com.eazybytes.Service.LeaveTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/leave-types")
public class LeaveTypeController {

    private final LeaveTypeService leaveTypeService;

    @Operation(summary = "Apply Leave", description = "Creates a new leave request.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Leave request submitted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid leave request"),
            @ApiResponse(responseCode = "409", description = "Overlapping leave request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/createLeave")
    public ResponseEntity<LeaveTypeDto> createLeave(@RequestBody LeaveTypeDto leaveTypeDto){
        return ResponseEntity.ok(leaveTypeService.createLeaveType(leaveTypeDto));
    }

    @GetMapping("/getAllLeaveTypes")
    public ResponseEntity<List<LeaveTypeDto>> getAllLeaveType(){
        return ResponseEntity.ok(leaveTypeService.getAllLeaveTypes());
    }

    @Operation(summary = "Get Leave By ID", description = "Retrieves leave request details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave request retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Leave request not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LeaveTypeDto> getLeaveTypeById(@PathVariable Long id){
        return ResponseEntity.ok(leaveTypeService.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveType(@PathVariable Long id){
        leaveTypeService.deleteLeaveType(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveTypeDto> updateLeaveType(
            @PathVariable Long id,
            @RequestBody LeaveTypeDto leaveTypeDto) {

        return ResponseEntity.ok(
                leaveTypeService.updateLeaveType(id, leaveTypeDto));
    }
}
