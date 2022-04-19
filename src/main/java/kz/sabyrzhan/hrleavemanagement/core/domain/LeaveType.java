package kz.sabyrzhan.hrleavemanagement.core.domain;

import kz.sabyrzhan.hrleavemanagement.core.domain.common.BaseDomainEntity;
import lombok.Data;

@Data
public class LeaveType extends BaseDomainEntity {
    private String name;
    private int defaultDays;
}
