package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class LeaveRequestListDTO extends BaseDTO {
    private LeaveTypeDTO leaveType;
    private Instant dateRequested;
    private boolean approved;
}
