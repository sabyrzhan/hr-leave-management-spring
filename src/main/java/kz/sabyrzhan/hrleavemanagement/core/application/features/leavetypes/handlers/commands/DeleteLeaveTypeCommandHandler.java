package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands.DeleteItemCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteLeaveTypeCommandHandler implements RequestHandler<DeleteItemCommand, Void> {
    private final LeaveTypeRepository leaveTypeRepository;

    public DeleteLeaveTypeCommandHandler(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<Void> handle(DeleteItemCommand request) {
        return leaveTypeRepository.deleteById(request.getId());
    }
}
