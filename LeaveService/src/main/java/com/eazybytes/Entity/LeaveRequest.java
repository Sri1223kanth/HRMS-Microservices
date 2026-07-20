package com.eazybytes.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Long employeeId;

//    private String leaveType;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private LeaveRequestStatus status;

    private String leaveReason;

    private LocalDate appliedDate;

    private LocalDate approvedDate;

    private String approvedBy;

    private Integer numberOfDays;
}
