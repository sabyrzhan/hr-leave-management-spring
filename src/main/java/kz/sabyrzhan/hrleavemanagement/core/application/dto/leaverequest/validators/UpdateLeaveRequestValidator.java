package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.validators;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveRequestRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.Validator;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.UpdateLeaveRequestDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveRequestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveRequestValidator implements Validator<UpdateLeaveRequestDTO> {
    private final CreateLeaveRequestValidator createLeaveRequestValidator;
    private final LeaveRequestRepository leaveRequestRepository;

    public UpdateLeaveRequestValidator(CreateLeaveRequestValidator createLeaveTypeValidator,
                                       LeaveRequestRepository leaveRequestRepository) {
        this.createLeaveRequestValidator = createLeaveTypeValidator;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public Mono<Boolean> validate(UpdateLeaveRequestDTO data) {
        return Mono
                .from(leaveRequestRepository.existsById(data.getId()))
                .map(exists -> !exists ? Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Leave request does not exist")) : Mono.just(true))
                .map(v -> LeaveRequestMapper.INSTANCE.copyFromUpdateRequestDTO(data))
                .flatMap(createDTO -> createLeaveRequestValidator.validate(createDTO))
                .thenReturn(true);
    }
}
