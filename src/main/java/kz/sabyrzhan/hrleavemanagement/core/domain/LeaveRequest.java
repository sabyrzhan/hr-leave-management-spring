package kz.sabyrzhan.hrleavemanagement.core.domain;

import kz.sabyrzhan.hrleavemanagement.core.domain.common.BaseDomainEntity;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table("leave_requests")
public class LeaveRequest extends BaseDomainEntity {
    @Column("start_date")
    private Instant startDate;
    @Column("end_date")
    private Instant endDate;
    private LeaveType leaveType;
    @Column("leave_type_id")
    private int leaveTypeId;
    @Column("date_requested")
    private Instant dateRequested;
    @Column("request_comments")
    private String requestComments;
    @Column("date_actioned")
    private Instant dateActioned;
    @Column("is_approved")
    private boolean approved;
    @Column("is_cancelled")
    private boolean cancelled;
}
