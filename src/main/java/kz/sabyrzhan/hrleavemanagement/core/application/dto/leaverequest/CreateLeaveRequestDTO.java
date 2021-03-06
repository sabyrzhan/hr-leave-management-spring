package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest;

import lombok.Data;

import java.time.Instant;

@Data
public class CreateLeaveRequestDTO {
    private Instant startDate;
    private Instant endDate;
    private int leaveTypeId;
    private String requestComments;
}
