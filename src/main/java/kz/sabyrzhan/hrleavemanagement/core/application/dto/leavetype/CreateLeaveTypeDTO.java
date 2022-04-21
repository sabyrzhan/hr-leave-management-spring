package kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype;

import lombok.Data;

@Data
public class CreateLeaveTypeDTO {
    private String name;
    private int defaultDays;
}
