package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import lombok.Getter;

import java.time.Instant;

@Getter
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

    public LeaveRequestDTO(LeaveRequest from) {
        this.id = from.getId();
        this.startDate = from.getStartDate();
        this.endDate = from.getEndDate();
        this.leaveType = new LeaveTypeDTO(from.getLeaveType());
        this.leaveTypeId = from.getLeaveTypeId();
        this.dateRequested = from.getDateRequested();
        this.requestComments = from.getRequestComments();
        this.dateActioned = from.getDateActioned();
        this.approved = from.isApproved();
        this.cancelled = from.isCancelled();
    }

    public LeaveRequest toLeaveRequest() {
        var leaveRequest = new LeaveRequest();
        leaveRequest.setId(id);
        leaveRequest.setStartDate(startDate);
        leaveRequest.setEndDate(endDate);
        leaveRequest.setLeaveType(leaveType.toLeaveType());
        leaveRequest.setLeaveTypeId(leaveTypeId);
        leaveRequest.setDateRequested(dateRequested);
        leaveRequest.setRequestComments(requestComments);
        leaveRequest.setDateActioned(dateActioned);
        leaveRequest.setApproved(approved);
        leaveRequest.setCancelled(cancelled);

        return leaveRequest;
    }
}
