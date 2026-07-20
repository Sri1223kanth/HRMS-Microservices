package com.AttendanceServiceApplication.Service;

import com.AttendanceServiceApplication.Client.EmployeeClient;
import com.AttendanceServiceApplication.Entity.Attendance;
import com.AttendanceServiceApplication.Entity.AttendanceStatus;
import com.AttendanceServiceApplication.Repository.AttendanceRepository;
import com.AttendanceServiceApplication.dto.AttendanceRequestDto;
import com.AttendanceServiceApplication.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final EmployeeClient employeeClient;

    public String markAttendance(AttendanceRequestDto requestDto) {


        boolean alreadyMarked = attendanceRepository.existsByEmployeeIdAndAttendanceDate(
                requestDto.getEmployeeId(), LocalDate.now());

        if(alreadyMarked){
            throw new RuntimeException("Attendance already marked today");
        }

        EmployeeDto employee =
                employeeClient.getEmployeeById(
                        requestDto.getEmployeeId()
                );


        if (employee == null) {

            throw new RuntimeException(
                    "Employee not found"
            );
        }


        System.out.println(employee.getName());

        Attendance attendance = Attendance.builder()
                .employeeId(employee.getId())
                .employeeEmail(employee.getEmail())
                .employeeName(employee.getName())
                .checkIn(requestDto.getCheck_in())
                .checkOut(requestDto.getCheck_out())
                .attendanceDate(LocalDate.now())
                .status(AttendanceStatus.valueOf(requestDto.getStatus()))
                .build();

        attendanceRepository.save(attendance);


        return "Attendance marked successfully";
    }

    public Attendance getAttendanceByEmployeeAndDate(
            Long employeeId,
            LocalDate date) {

        return (Attendance) attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, date)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found"));
    }

    public Page<Attendance> getAllAttendance(Pageable pageable) {

        return attendanceRepository.findAll(pageable);
    }

    public String checkOut(AttendanceRequestDto requestDto) {

        Attendance attendance = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(
                        requestDto.getEmployeeId(),
                        LocalDate.now())
                .orElseThrow(() ->
                        new RuntimeException("Employee has not checked in today"));

        attendance.setCheckOut(requestDto.getCheck_out());

        attendanceRepository.save(attendance);

        return "Checked out successfully";
    }
}