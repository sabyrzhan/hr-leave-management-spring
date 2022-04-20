package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.queries;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.queries.GetLeaveTypeDetailRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetLeaveTypeDetailRequestHandler implements RequestHandler<GetLeaveTypeDetailRequest, LeaveTypeDTO> {
    private final LeaveTypeRepository leaveTypeRepository;

    public GetLeaveTypeDetailRequestHandler(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<LeaveTypeDTO> handle(GetLeaveTypeDetailRequest request) {
        return leaveTypeRepository.findById(request.getId())
                .map(LeaveTypeDTO::new)
                .onErrorResume(t -> Mono.error(t));
    }
}
