package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation;

import lombok.Data;

@Data
public class CreateLeaveAllocationDTO {
    private int numberOfDays;
    private int leaveTypeId;
    private int period;
}
