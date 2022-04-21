package kz.sabyrzhan.hrleavemanagement.core.domain.common;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;

@Data
public abstract class BaseDomainEntity {
    @Id
    protected int id;
    @Column("date_created")
    protected Instant dateCreated;
    @Column("created_by")
    protected String createdBy;
    @Column("last_modified_date")
    protected Instant lastModifiedDate;
    @Column("last_modified_by")
    protected String lastModifiedBy;
}
