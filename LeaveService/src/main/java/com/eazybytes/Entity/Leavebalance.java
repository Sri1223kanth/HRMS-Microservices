package com.eazybytes.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leavebalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Long employeeId;

    private String leaveType;

    private Integer remainingLeaves;

    private Integer totalLeaves;

    private Integer usedLeaves;

    private Year year;


}
