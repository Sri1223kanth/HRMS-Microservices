package com.eazybytes.Service;

import com.eazybytes.Dto.LeaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LeaveRequestService {


    LeaveRequestDto createLeaveRequest(LeaveRequestDto dto);

    List<LeaveRequestDto> getAllLeaveRequests();

    LeaveRequestDto getLeaveRequestById(Long id);

    LeaveRequestDto updateLeaveRequest(Long id, LeaveRequestDto dto);

    void deleteLeaveRequest(Long id);


}
