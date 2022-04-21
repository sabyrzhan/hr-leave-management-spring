package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveRequestRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands.UpdateLeaveRequestCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveRequestMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveRequestCommandHandler implements RequestHandler<UpdateLeaveRequestCommand, Void> {
    private final LeaveRequestRepository leaveRequestRepository;

    public UpdateLeaveRequestCommandHandler(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public Mono<Void> handle(UpdateLeaveRequestCommand request) {
        return leaveRequestRepository.findById(request.getLeaveRequest().getId())
                .flatMap(existing -> {
                    LeaveRequestMapper.INSTANCE.updateLeaveRequest(request.getLeaveRequest(), existing);
                    return leaveRequestRepository.save(existing).then();
                });
    }
}
