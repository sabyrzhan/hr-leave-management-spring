package kz.sabyrzhan.hrleavemanagement.core.domain;

import kz.sabyrzhan.hrleavemanagement.core.domain.common.BaseDomainEntity;
import lombok.Data;

@Data
public class LeaveAllocation extends BaseDomainEntity {
    private int numberOfDays;
    private LeaveType leaveType;
    private int leaveTypeId;
    private int period;
}
