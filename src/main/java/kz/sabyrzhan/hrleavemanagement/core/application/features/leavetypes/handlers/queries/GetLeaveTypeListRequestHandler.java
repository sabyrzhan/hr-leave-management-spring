package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.queries;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.queries.GetLeaveTypeListRequest;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveTypeMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GetLeaveTypeListRequestHandler implements RequestHandler<GetLeaveTypeListRequest, List<LeaveTypeDTO>> {
    private final LeaveTypeRepository leaveTypeRepository;

    public GetLeaveTypeListRequestHandler(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<List<LeaveTypeDTO>> handle(GetLeaveTypeListRequest request) {
        return leaveTypeRepository
                .findAll().collectList()
                .map(leaveTypes -> leaveTypes.stream().map(LeaveTypeMapper.INSTANCE::createFromLeaveType).toList());
    }
}
