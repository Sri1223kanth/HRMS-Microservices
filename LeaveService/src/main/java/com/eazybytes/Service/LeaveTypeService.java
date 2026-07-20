package com.eazybytes.Service;



import com.eazybytes.Dto.LeaveTypeDto;

import com.eazybytes.Entity.LeaveType;

import com.eazybytes.Exception.LeaveTypeNotFoundException;

import com.eazybytes.Repository.LeaveBalanceRepository;

import com.eazybytes.Repository.LeaveTypeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;

    public List<LeaveTypeDto> getAllLeaveTypes() {
        return leaveTypeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LeaveTypeDto findById(Long id) {

        LeaveType leaveType = leaveTypeRepository.findById(id)
                .orElseThrow(() -> new LeaveTypeNotFoundException(id));

        return convertToDto(leaveType);
    }

    public LeaveTypeDto createLeaveType(LeaveTypeDto dto) {

        LeaveType leaveType = convertToEntity(dto);

        LeaveType saved = leaveTypeRepository.save(leaveType);

        return convertToDto(saved);
    }

    public LeaveTypeDto updateLeaveType(Long id, LeaveTypeDto dto) {

        LeaveType leaveType = leaveTypeRepository.findById(id)
                .orElseThrow(() -> new LeaveTypeNotFoundException(id));

        leaveType.setName(dto.getName());
        leaveType.setTotalLeaves(dto.getTotalLeaves());
        leaveType.setIsPaid(dto.getIsPaid());
        leaveType.setStatus(dto.getStatus());

        return convertToDto(leaveTypeRepository.save(leaveType));
    }

    public LeaveTypeDto deleteLeaveType(Long id) {

        LeaveType leaveType = leaveTypeRepository.findById(id)
                .orElseThrow(() -> new LeaveTypeNotFoundException(id));

        leaveTypeRepository.delete(leaveType);

        return convertToDto(leaveType);
    }

    private LeaveTypeDto convertToDto(LeaveType leaveType) {

        LeaveTypeDto dto = new LeaveTypeDto();

        dto.setId(leaveType.getId());
        dto.setName(leaveType.getName());
        dto.setTotalLeaves(leaveType.getTotalLeaves());
        dto.setIsPaid(leaveType.getIsPaid());
        dto.setStatus(leaveType.getStatus());

        return dto;
    }

    private LeaveType convertToEntity(LeaveTypeDto dto) {

        LeaveType leaveType = new LeaveType();

        leaveType.setName(dto.getName());
        leaveType.setTotalLeaves(dto.getTotalLeaves());
        leaveType.setIsPaid(dto.getIsPaid());
        leaveType.setStatus(dto.getStatus());

        return leaveType;
    }
}