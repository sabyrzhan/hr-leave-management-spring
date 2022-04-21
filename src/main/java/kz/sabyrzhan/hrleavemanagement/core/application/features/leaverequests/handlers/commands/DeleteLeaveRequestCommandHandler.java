package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveRequestRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands.DeleteItemCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteLeaveRequestCommandHandler implements RequestHandler<DeleteItemCommand, Void> {
    private final LeaveRequestRepository leaveRequestRepository;

    public DeleteLeaveRequestCommandHandler(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public Mono<Void> handle(DeleteItemCommand request) {
        return leaveRequestRepository.deleteById(request.getId());
    }
}
