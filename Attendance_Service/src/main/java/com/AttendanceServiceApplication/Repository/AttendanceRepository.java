package com.AttendanceServiceApplication.Repository;

import com.AttendanceServiceApplication.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    boolean existsByEmployeeIdAndAttendanceDate(Long employeeId,
                                                LocalDate attendanceDate);

//    Optional<Object> findByEmployeeIdAndAttendanceDate(Long employeeId, LocalDate date);

    Optional<Attendance> findByEmployeeIdAndAttendanceDate(
            Long employeeId,
            LocalDate attendanceDate);
}
