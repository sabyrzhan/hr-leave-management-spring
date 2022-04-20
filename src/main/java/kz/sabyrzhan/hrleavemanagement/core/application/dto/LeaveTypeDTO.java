package kz.sabyrzhan.hrleavemanagement.core.application.dto;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveType;
import lombok.Getter;

@Getter
public class LeaveTypeDTO extends BaseDTO {
    private String name;
    private int defaultDays;

    public LeaveTypeDTO(LeaveType from) {
        this.id = from.getId();
        this.name = from.getName();
        this.defaultDays = from.getDefaultDays();
    }
}
