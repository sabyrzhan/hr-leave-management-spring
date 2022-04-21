package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.queries;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveAllocationRepositoryExtended;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.LeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.queries.GetLeaveAllocationsDetailRequest;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveAllocationMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetLeaveAllocationDetailRequestHandler implements RequestHandler<GetLeaveAllocationsDetailRequest, LeaveAllocationDTO> {
    private final LeaveAllocationRepositoryExtended leaveAllocationService;

    public GetLeaveAllocationDetailRequestHandler(LeaveAllocationRepositoryExtended leaveAllocationService) {
        this.leaveAllocationService = leaveAllocationService;
    }

    @Override
    public Mono<LeaveAllocationDTO> handle(GetLeaveAllocationsDetailRequest request) {
        return leaveAllocationService.findById(request.getId())
                .map(LeaveAllocationMapper.INSTANCE::convertToDTO)
                .onErrorResume(t -> Mono.error(t));
    }
}
