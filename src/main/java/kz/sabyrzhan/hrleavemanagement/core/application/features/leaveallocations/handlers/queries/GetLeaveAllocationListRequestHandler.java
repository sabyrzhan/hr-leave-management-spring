package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.queries;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveAllocationRepositoryExtended;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.LeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.queries.GetLeaveAllocationsListRequest;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveAllocationMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GetLeaveAllocationListRequestHandler implements RequestHandler<GetLeaveAllocationsListRequest, List<LeaveAllocationDTO>> {
    private final LeaveAllocationRepositoryExtended leaveAllocationRepositoryExtended;

    public GetLeaveAllocationListRequestHandler(LeaveAllocationRepositoryExtended service) {
        this.leaveAllocationRepositoryExtended = service;
    }

    @Override
    public Mono<List<LeaveAllocationDTO>> handle(GetLeaveAllocationsListRequest request) {
        return leaveAllocationRepositoryExtended.findAll()
                .map(list -> list.stream().map(LeaveAllocationMapper.INSTANCE::convertToDTO).toList());
    }
}
