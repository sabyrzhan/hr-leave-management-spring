package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveAllocationRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.LeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands.UpdateLeaveAllocationCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveAllocationMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveAllocationCommandHandler implements RequestHandler<UpdateLeaveAllocationCommand, LeaveAllocationDTO> {
    private final LeaveAllocationRepository leaveAllocationRepository;

    public UpdateLeaveAllocationCommandHandler(LeaveAllocationRepository leaveAllocationRepository) {
        this.leaveAllocationRepository = leaveAllocationRepository;
    }

    @Override
    public Mono<LeaveAllocationDTO> handle(UpdateLeaveAllocationCommand request) {
        return leaveAllocationRepository.findById(request.getLeaveAllocation().getId())
                .flatMap(existing -> {
                    LeaveAllocationMapper.INSTANCE.updateLeaveAllocation(request.getLeaveAllocation(), existing);
                    return leaveAllocationRepository.save(existing);
                })
                .map(LeaveAllocationMapper.INSTANCE::convertToDTO);
    }
}
