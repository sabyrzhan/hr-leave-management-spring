package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import lombok.Getter;

import java.time.Instant;

@Getter
public class LeaveRequestListDTO extends BaseDTO {
    private LeaveTypeDTO leaveType;
    private Instant dateRequested;
    private boolean approved;

    public LeaveRequestListDTO(LeaveRequest from) {
        this.leaveType = new LeaveTypeDTO(from.getLeaveType());
        this.dateRequested = from.getDateRequested();
        this.approved = from.isApproved();
    }
}
