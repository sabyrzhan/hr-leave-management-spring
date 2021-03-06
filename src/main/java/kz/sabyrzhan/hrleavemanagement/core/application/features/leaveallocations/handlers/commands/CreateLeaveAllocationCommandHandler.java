package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveAllocationRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.LeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.validators.CreateLeaveAllocationValidator;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands.CreateLeaveAllocationCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveAllocationMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateLeaveAllocationCommandHandler implements RequestHandler<CreateLeaveAllocationCommand, LeaveAllocationDTO> {
    private final CreateLeaveAllocationValidator validator;
    private final LeaveAllocationRepository repository;

    public CreateLeaveAllocationCommandHandler(LeaveAllocationRepository repository,
                                               CreateLeaveAllocationValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Mono<LeaveAllocationDTO> handle(CreateLeaveAllocationCommand request) {
        return Mono
                .from(validator.validate(request.getLeaveAllocation()))
                .map(v -> LeaveAllocationMapper.INSTANCE.createFromDTO(request.getLeaveAllocation()))
                .flatMap(repository::save)
                .map(LeaveAllocationMapper.INSTANCE::convertToDTO);
    }
}
