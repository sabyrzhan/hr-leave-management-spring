package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands.CreateLeaveTypeCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateLeaveTypeCommandHandler implements RequestHandler<CreateLeaveTypeCommand, Integer> {
    private final LeaveTypeRepository leaveTypeRepository;

    public CreateLeaveTypeCommandHandler(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<Integer> handle(CreateLeaveTypeCommand request) {
        var leaveType = request.getLeaveType().toLeaveType();
        return leaveTypeRepository.save(leaveType).map(result -> result.getId());
    }
}
