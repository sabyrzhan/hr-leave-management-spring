package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveRequestRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands.ChangeLeaveRequestApprovalCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveRequestMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ChangeLeaveRequestApprovalCommandHandler implements RequestHandler<ChangeLeaveRequestApprovalCommand, Void> {
    private final LeaveRequestRepository leaveRequestRepository;

    public ChangeLeaveRequestApprovalCommandHandler(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public Mono<Void> handle(ChangeLeaveRequestApprovalCommand request) {
        return leaveRequestRepository.findById(request.getChangeApproval().getId())
                .flatMap(existing -> {
                    LeaveRequestMapper.INSTANCE.updateChangeApproval(request.getChangeApproval(), existing);
                    return leaveRequestRepository.save(existing).then();
                });
    }
}
