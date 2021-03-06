package kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence;

import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LeaveRequestRepository extends ReactiveCrudRepository<LeaveRequest, Integer> {
}
