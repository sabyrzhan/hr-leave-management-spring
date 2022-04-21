package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveAllocationRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands.CreateLeaveAllocationCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveAllocationMapper;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveAllocation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateLeaveAllocationCommandHandler implements RequestHandler<CreateLeaveAllocationCommand, Integer> {
    private final LeaveAllocationRepository repository;

    public CreateLeaveAllocationCommandHandler(LeaveAllocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Integer> handle(CreateLeaveAllocationCommand request) {
        var leaveAllocation = LeaveAllocationMapper.INSTANCE.createFromDTO(request.getLeaveAllocation());
        return repository.save(leaveAllocation).map(LeaveAllocation::getId);
    }
}
