package kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype;

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

    public LeaveType toLeaveType() {
        var leaveType = new LeaveType();
        leaveType.setId(getId());
        leaveType.setName(name);
        leaveType.setDefaultDays(defaultDays);

        return leaveType;
    }
}
