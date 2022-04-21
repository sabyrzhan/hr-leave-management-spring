package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import lombok.Data;

@Data
public class LeaveAllocationDTO extends BaseDTO {
    private int numberOfDays;
    private LeaveTypeDTO leaveType;
    private int leaveTypeId;
    private int period;
}
