package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import lombok.Data;

@Data
public class UpdateLeaveTypeCommand {
    private LeaveTypeDTO leaveType;
}
