package kz.sabyrzhan.hrleavemanagement.core.application.contracts;

import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveAllocation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LeaveAllocationRepository extends ReactiveCrudRepository<LeaveAllocation, Integer> {
}
