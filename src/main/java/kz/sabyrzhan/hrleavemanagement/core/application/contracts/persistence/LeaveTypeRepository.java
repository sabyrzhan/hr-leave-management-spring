package kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence;

import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LeaveTypeRepository extends ReactiveCrudRepository<LeaveType, Integer> {
}
