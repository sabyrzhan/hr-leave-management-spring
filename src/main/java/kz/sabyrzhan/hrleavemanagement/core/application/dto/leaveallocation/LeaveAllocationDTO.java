package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
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

    public LeaveAllocation toLeaveAllocation() {
        var leaveAllocation = new LeaveAllocation();
        leaveAllocation.setId(getId());
        leaveAllocation.setLeaveType(leaveType.toLeaveType());
        leaveAllocation.setLeaveTypeId(leaveTypeId);
        leaveAllocation.setPeriod(period);

        return leaveAllocation;
    }
}
