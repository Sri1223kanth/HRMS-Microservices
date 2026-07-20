package com.eazybytes.Controller;

import com.eazybytes.Dto.LeaveBalanceDto;
import com.eazybytes.Service.LeaveBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leave-balances")
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    @PostMapping
    public ResponseEntity<LeaveBalanceDto> createLeaveBalance(
            @RequestBody LeaveBalanceDto dto) {

        return ResponseEntity.ok(
                leaveBalanceService.createLeaveBalance(dto));
    }

    @GetMapping
    public ResponseEntity<List<LeaveBalanceDto>> getAllLeaveBalances() {

        return ResponseEntity.ok(
                leaveBalanceService.getAllLeaveBalances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveBalanceDto> getLeaveBalanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveBalanceService.getLeaveBalanceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveBalanceDto> updateLeaveBalance(
            @PathVariable Long id,
            @RequestBody LeaveBalanceDto dto) {

        return ResponseEntity.ok(
                leaveBalanceService.updateLeaveBalance(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveBalance(
            @PathVariable Long id) {

        leaveBalanceService.deleteLeaveBalance(id);

        return ResponseEntity.noContent().build();
    }
}
