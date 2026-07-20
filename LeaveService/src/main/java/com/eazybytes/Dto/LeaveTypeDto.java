package com.eazybytes.Dto;

import com.eazybytes.Entity.LeaveTypeStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LeaveTypeDto {

    private Long id;

    private String name;

    private Integer totalLeaves;

    private Boolean isPaid;

    private LeaveTypeStatus status;
}
