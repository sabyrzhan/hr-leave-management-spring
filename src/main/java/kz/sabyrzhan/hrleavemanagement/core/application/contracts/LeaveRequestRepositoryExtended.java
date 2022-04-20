package kz.sabyrzhan.hrleavemanagement.core.application.contracts;

import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class LeaveRequestRepositoryExtended {
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveRequestRepositoryExtended(LeaveRequestRepository leaveRequestRepository,
                                          LeaveTypeRepository leaveTypeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public Mono<List<LeaveRequest>> findAll() {
        var holder = new Holder();
        return leaveRequestRepository.findAll().collectList()
                .flatMap(list -> {
                    holder.list = list;
                    return leaveTypeRepository.findAll().collectList();
                })
                .map(RepositoryUtils::leaveTypesToMap)
                .map(typesMap -> {
                    holder.list.stream().forEach(i -> i.setLeaveType(typesMap.get(i.getLeaveTypeId())));
                    return holder.list;
                });
    }

    public Mono<LeaveRequest> findById(int id) {
        var holder = new Holder();
        return leaveRequestRepository.findById(id)
                .flatMap(item -> {
                    holder.leaveRequest = item;
                    return leaveTypeRepository.findById(id);
                })
                .map(type -> {
                    holder.leaveRequest.setLeaveType(type);
                    return holder.leaveRequest;
                });
    }

    private static class Holder {
        private List<LeaveRequest> list;
        private LeaveRequest leaveRequest;
    }
}
