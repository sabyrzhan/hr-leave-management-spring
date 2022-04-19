package kz.sabyrzhan.hrleavemanagement.core.domain;

import kz.sabyrzhan.hrleavemanagement.core.domain.common.BaseDomainEntity;
import lombok.Data;

import java.time.Instant;

@Data
public class LeaveRequest extends BaseDomainEntity {
    private Instant startDate;
    private Instant endDate;
    private LeaveType leaveType;
    private int leaveTypeId;
    private Instant dateRequested;
    private String requestComments;
    private Instant dateActioned;
    private boolean approved;
    private boolean cancelled;
}
