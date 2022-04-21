package kz.sabyrzhan.hrleavemanagement.core.domain;

import kz.sabyrzhan.hrleavemanagement.core.domain.common.BaseDomainEntity;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("leave_allocations")
public class LeaveAllocation extends BaseDomainEntity {
    @Column("number_of_days")
    private int numberOfDays;
    private LeaveType leaveType;
    @Column("leave_type_id")
    private int leaveTypeId;
    @Column("period")
    private int period;
}
