package kz.sabyrzhan.hrleavemanagement.core.application.dto;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveAllocation;
import lombok.Getter;

@Getter
public class LeaveAllocationDTO extends BaseDTO {
    private int numberOfDays;
    private LeaveTypeDTO leaveType;
    private int leaveTypeId;
    private int period;

    public LeaveAllocationDTO(LeaveAllocation from) {
        this.id = from.getId();
        this.numberOfDays = from.getNumberOfDays();
        this.leaveType = new LeaveTypeDTO(from.getLeaveType());
        this.leaveTypeId = this.leaveType.getId();
        this.period = from.getPeriod();
    }
}
