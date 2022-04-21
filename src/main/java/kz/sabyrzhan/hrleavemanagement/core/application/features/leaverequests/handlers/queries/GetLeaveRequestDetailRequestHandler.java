package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.queries;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveRequestRepositoryExtended;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.LeaveRequestDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.queries.GetLeaveRequestDetailRequest;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveRequestMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetLeaveRequestDetailRequestHandler implements RequestHandler<GetLeaveRequestDetailRequest, LeaveRequestDTO> {
    private final LeaveRequestRepositoryExtended leaveRequestRepositoryExtended;

    public GetLeaveRequestDetailRequestHandler(LeaveRequestRepositoryExtended repository) {
        this.leaveRequestRepositoryExtended = repository;
    }

    @Override
    public Mono<LeaveRequestDTO> handle(GetLeaveRequestDetailRequest request) {
        return leaveRequestRepositoryExtended.findById(request.getId())
                .map(LeaveRequestMapper.INSTANCE::convertToDTO)
                .onErrorResume(t -> Mono.error(t));
    }
}
