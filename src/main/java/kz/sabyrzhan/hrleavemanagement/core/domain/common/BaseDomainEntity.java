package kz.sabyrzhan.hrleavemanagement.core.domain.common;

import lombok.Data;

import java.time.Instant;

@Data
public abstract class BaseDomainEntity {
    protected int id;
    protected Instant dateCreated;
    protected String createdBy;
    protected Instant lastModifiedDate;
    protected String lastModifiedBy;
}
