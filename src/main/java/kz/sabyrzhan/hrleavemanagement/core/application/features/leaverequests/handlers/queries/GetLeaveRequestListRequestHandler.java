package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.queries;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveRequestRepositoryExtended;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.LeaveRequestListDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.queries.GetLeaveRequestListRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GetLeaveRequestListRequestHandler implements RequestHandler<GetLeaveRequestListRequest, List<LeaveRequestListDTO>> {
    private final LeaveRequestRepositoryExtended leaveRequestRepositoryExtended;

    public GetLeaveRequestListRequestHandler(LeaveRequestRepositoryExtended repository) {
        this.leaveRequestRepositoryExtended = repository;
    }

    @Override
    public Mono<List<LeaveRequestListDTO>> handle(GetLeaveRequestListRequest request) {
        return leaveRequestRepositoryExtended.findAll()
                .map(list -> list.stream().map(LeaveRequestListDTO::new).toList());
    }
}
