package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveRequestRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.contracts.services.EmailSender;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands.CreateLeaveRequestCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveRequestMapper;
import kz.sabyrzhan.hrleavemanagement.core.application.models.Email;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateLeaveRequestCommandHandler implements RequestHandler<CreateLeaveRequestCommand, Integer> {
    private final LeaveRequestRepository repository;
    private final EmailSender emailSender;

    public CreateLeaveRequestCommandHandler(LeaveRequestRepository repository,
                                            EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    @Override
    public Mono<Integer> handle(CreateLeaveRequestCommand request) {
        var leaveRequest = LeaveRequestMapper.INSTANCE.createFromDTO(request.getLeaveRequest());
        return repository
                .save(leaveRequest)
                .map(LeaveRequest::getId)
                .flatMap(id -> {
                    var email =  Email.builder()
                            .to("employee@org.com")
                            .body("Your leave request for " + leaveRequest.getStartDate() + " to " + leaveRequest.getEndDate() + " has been submitted successfully!")
                            .subject("Leave request submitted")
                            .build();
                    return emailSender.sendEmail(email).map(v -> id);
                });
    }
}
