package kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands.UpdateLeaveTypeCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveTypeMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveTypeCommandHandler implements RequestHandler<UpdateLeaveTypeCommand, LeaveTypeDTO> {
    private final LeaveTypeRepository leaveTypeRepository;

    public UpdateLeaveTypeCommandHandler(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<LeaveTypeDTO> handle(UpdateLeaveTypeCommand request) {
        return leaveTypeRepository.findById(request.getLeaveType().getId())
                .flatMap(existing -> {
                    LeaveTypeMapper.INSTANCE.updateLeaveType(request.getLeaveType(), existing);
                    return leaveTypeRepository.save(existing);
                })
                .map(saved -> LeaveTypeMapper.INSTANCE.createFromLeaveType(saved));
    }
}
