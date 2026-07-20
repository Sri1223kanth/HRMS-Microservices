package com.eazybytes.Controller;

import com.eazybytes.Dto.LeaveRequestDto;
import com.eazybytes.Service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(
            @RequestBody LeaveRequestDto dto) {

        return ResponseEntity.ok(
                leaveRequestService.createLeaveRequest(dto));
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequestDto>> getAllLeaveRequests() {

        return ResponseEntity.ok(
                leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getLeaveRequestById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveRequestService.getLeaveRequestById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> updateLeaveRequest(
            @PathVariable Long id,
            @RequestBody LeaveRequestDto dto) {

        return ResponseEntity.ok(
                leaveRequestService.updateLeaveRequest(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(
            @PathVariable Long id) {

        leaveRequestService.deleteLeaveRequest(id);

        return ResponseEntity.noContent().build();
    }
}
