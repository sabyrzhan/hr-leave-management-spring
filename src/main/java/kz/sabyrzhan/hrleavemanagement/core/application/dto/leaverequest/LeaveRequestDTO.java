package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveType;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class LeaveRequestDTO extends BaseDTO {
    private Instant startDate;
    private Instant endDate;
    private LeaveTypeDTO leaveType;
    private int leaveTypeId;
    private Instant dateRequested;
    private String requestComments;
    private Instant dateActioned;
    private boolean approved;
    private boolean cancelled;
}
