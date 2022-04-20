package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import lombok.Data;

@Data
public class CreateLeaveAllocationDTO extends BaseDTO {
    private int numberOfDays;
    private int leaveTypeId;
    private int period;
}
