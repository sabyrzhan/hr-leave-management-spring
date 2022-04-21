package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.UpdateLeaveRequestDTO;
import lombok.Data;

@Data
public class UpdateLeaveRequestCommand {
    private UpdateLeaveRequestDTO leaveRequest;
}
