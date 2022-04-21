package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.validators.CreateLeaveTypeValidator;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands.CreateLeaveTypeCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveTypeMapper;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateLeaveTypeCommandHandler implements RequestHandler<CreateLeaveTypeCommand, Integer> {
    private final LeaveTypeRepository leaveTypeRepository;
    private final CreateLeaveTypeValidator createLeaveTypeValidator;

    public CreateLeaveTypeCommandHandler(LeaveTypeRepository leaveTypeRepository,
                                         CreateLeaveTypeValidator createLeaveTypeValidator) {
        this.leaveTypeRepository = leaveTypeRepository;
        this.createLeaveTypeValidator = createLeaveTypeValidator;
    }

    @Override
    public Mono<Integer> handle(CreateLeaveTypeCommand request) {
        return Mono
                .defer(() -> createLeaveTypeValidator.validate(request.getLeaveType()))
                .map(v -> LeaveTypeMapper.INSTANCE.createFromDTO(request.getLeaveType()))
                .flatMap(leaveType -> leaveTypeRepository.save(leaveType).map(LeaveType::getId));
    }
}
