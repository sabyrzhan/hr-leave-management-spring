package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveRequestRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands.CreateLeaveRequestCommand;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateLeaveRequestCommandHandler implements RequestHandler<CreateLeaveRequestCommand, Integer> {
    private final LeaveRequestRepository repository;

    public CreateLeaveRequestCommandHandler(LeaveRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Integer> handle(CreateLeaveRequestCommand request) {
        var leaveRequest = request.getLeaveRequest().toLeaveRequest();
        return repository.save(leaveRequest).map(LeaveRequest::getId);
    }
}
