package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveAllocationRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands.UpdateLeaveAllocationCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveAllocationMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveAllocationCommandHandler implements RequestHandler<UpdateLeaveAllocationCommand, Void> {
    private final LeaveAllocationRepository leaveAllocationRepository;

    public UpdateLeaveAllocationCommandHandler(LeaveAllocationRepository leaveAllocationRepository) {
        this.leaveAllocationRepository = leaveAllocationRepository;
    }

    @Override
    public Mono<Void> handle(UpdateLeaveAllocationCommand request) {
        return leaveAllocationRepository.findById(request.getLeaveAllocation().getId())
                .flatMap(existing -> {
                    LeaveAllocationMapper.INSTANCE.updateLeaveAllocation(request.getLeaveAllocation(), existing);
                    return leaveAllocationRepository.save(existing).then();
                });
    }
}
