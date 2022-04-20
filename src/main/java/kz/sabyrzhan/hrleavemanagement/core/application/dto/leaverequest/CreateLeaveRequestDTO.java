package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class CreateLeaveRequestDTO extends BaseDTO {
    private Instant startDate;
    private Instant endDate;
    private int leaveTypeId;
    private String requestComments;
}
