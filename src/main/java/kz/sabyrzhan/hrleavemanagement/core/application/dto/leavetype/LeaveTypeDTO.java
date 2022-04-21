package kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import lombok.Data;

@Data
public class LeaveTypeDTO extends BaseDTO {
    private String name;
    private int defaultDays;
}
