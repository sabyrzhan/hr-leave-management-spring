package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.CreateLeaveTypeDTO;
import lombok.Data;

@Data
public class CreateLeaveTypeCommand {
    private CreateLeaveTypeDTO leaveType;
}
