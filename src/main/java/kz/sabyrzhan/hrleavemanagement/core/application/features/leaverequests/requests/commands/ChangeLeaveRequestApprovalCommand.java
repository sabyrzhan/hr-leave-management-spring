package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.ChangeLeaveRequestApprovalDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChangeLeaveRequestApprovalCommand {
    private final ChangeLeaveRequestApprovalDTO changeApproval;
}
