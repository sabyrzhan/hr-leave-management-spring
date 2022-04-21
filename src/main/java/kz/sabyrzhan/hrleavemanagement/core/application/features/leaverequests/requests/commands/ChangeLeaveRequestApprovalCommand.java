package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.ChangeLeaveRequestApprovalDTO;
import lombok.Data;

@Data
public class ChangeLeaveRequestApprovalCommand {
    private ChangeLeaveRequestApprovalDTO changeApproval;
}
