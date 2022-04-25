package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.UpdateLeaveRequestDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateLeaveRequestCommand {
    private final UpdateLeaveRequestDTO leaveRequest;
}
