package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.LeaveRequestDTO;
import lombok.Data;

@Data
public class CreateLeaveRequestCommand {
    private LeaveRequestDTO leaveRequest;
}
