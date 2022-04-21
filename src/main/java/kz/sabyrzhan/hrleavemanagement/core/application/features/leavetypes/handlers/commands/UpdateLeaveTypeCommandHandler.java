package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands.UpdateLeaveTypeCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveTypeMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveTypeCommandHandler implements RequestHandler<UpdateLeaveTypeCommand, Void> {
    private final LeaveTypeRepository leaveTypeRepository;

    public UpdateLeaveTypeCommandHandler(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<Void> handle(UpdateLeaveTypeCommand request) {
        return leaveTypeRepository.findById(request.getLeaveType().getId())
                .flatMap(existing -> {
                    LeaveTypeMapper.INSTANCE.updateLeaveType(request.getLeaveType(), existing);
                    return leaveTypeRepository.save(existing).then();
                });
    }
}
