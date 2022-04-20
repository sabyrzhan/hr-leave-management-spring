package kz.sabyrzhan.hrleavemanagement.core.application.contracts;

import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveAllocation;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveType;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class LeaveAllocationRepositoryExtended {
    private final LeaveAllocationRepository leaveAllocationRepository;
    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveAllocationRepositoryExtended(LeaveAllocationRepository leaveAllocationRepository,
                                             LeaveTypeRepository leaveTypeRepository) {
        this.leaveAllocationRepository = leaveAllocationRepository;
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public Mono<LeaveAllocation> findById(int id) {
        var holder = new ContextHolder();
        return leaveAllocationRepository.findById(id)
                .flatMap(leaveAllocation -> {
                    holder.leaveAllocation = leaveAllocation;
                    return leaveTypeRepository.findById(leaveAllocation.getLeaveTypeId());
                })
                .map(leaveType -> {
                    holder.leaveAllocation.setLeaveType(leaveType);
                    return holder.leaveAllocation;
                });
    }

    public Mono<List<LeaveAllocation>> findAll() {
        var holder = new ContextHolder();
        return leaveAllocationRepository.findAll().collectList()
                .flatMap(list -> {
                    holder.list = list;
                    return leaveTypeRepository.findAll().collectList();
                })
                .map(leaveTypes -> leaveTypes.stream().collect(Collectors.toMap(LeaveType::getId, Function.identity())))
                .map(leaveTypesMap -> {
                    for(var allocation : holder.list) {
                        allocation.setLeaveType(leaveTypesMap.get(allocation.getLeaveTypeId()));
                    }

                    return holder.list;
                });
    }

    private static class ContextHolder {
        private LeaveAllocation leaveAllocation;
        private List<LeaveAllocation> list;
    }
}
