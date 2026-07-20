package com.eazybytes.Service;

import com.eazybytes.Dto.LeaveBalanceDto;

import java.util.List;

public interface LeaveBalanceService {

    LeaveBalanceDto createLeaveBalance(LeaveBalanceDto dto);

    List<LeaveBalanceDto> getAllLeaveBalances();

    LeaveBalanceDto getLeaveBalanceById(Long id);

    LeaveBalanceDto updateLeaveBalance(Long id, LeaveBalanceDto dto);

    void deleteLeaveBalance(Long id);
}
