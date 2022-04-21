package kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.validators;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.Validator;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveTypeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveTypeValidator implements Validator<LeaveTypeDTO> {
    private final CreateLeaveTypeValidator createLeaveTypeValidator;
    private final LeaveTypeRepository leaveTypeRepository;

    public UpdateLeaveTypeValidator(CreateLeaveTypeValidator createLeaveTypeValidator,
                                    LeaveTypeRepository leaveTypeRepository) {
        this.createLeaveTypeValidator = createLeaveTypeValidator;
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<Boolean> validate(LeaveTypeDTO data) {
        return Mono
                .from(leaveTypeRepository.existsById(data.getId()))
                .map(exists -> !exists ? Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Leave type does not exist")) : Mono.just(true))
                .map(v -> LeaveTypeMapper.INSTANCE.copyFromLeaveTypeDTO(data))
                .map(createLeaveTypeValidator::validate)
                .thenReturn(true);
    }
}
