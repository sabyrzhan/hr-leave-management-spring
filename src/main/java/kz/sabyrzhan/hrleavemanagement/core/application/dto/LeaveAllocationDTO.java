package kz.sabyrzhan.hrleavemanagement.core.application.dto;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LeaveAllocationDTO extends BaseDTO {
    private int numberOfDays;
    private LeaveTypeDTO leaveType;
    private int leaveTypeId;
    private int period;
}
