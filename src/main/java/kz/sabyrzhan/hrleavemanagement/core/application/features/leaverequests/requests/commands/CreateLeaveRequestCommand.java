package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.CreateLeaveRequestDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateLeaveRequestCommand {
    private final CreateLeaveRequestDTO leaveRequest;
}
