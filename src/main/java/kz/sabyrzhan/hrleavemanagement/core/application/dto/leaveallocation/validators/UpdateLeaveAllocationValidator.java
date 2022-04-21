package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.validators;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveAllocationRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.Validator;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.UpdateLeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.mappers.LeaveAllocationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class UpdateLeaveAllocationValidator implements Validator<UpdateLeaveAllocationDTO> {
    private final LeaveAllocationRepository leaveAllocationRepository;
    private final CreateLeaveAllocationValidator createLeaveAllocationValidator;

    public UpdateLeaveAllocationValidator(LeaveAllocationRepository leaveAllocationRepository,
                                          CreateLeaveAllocationValidator createLeaveAllocationValidator) {
        this.leaveAllocationRepository = leaveAllocationRepository;
        this.createLeaveAllocationValidator = createLeaveAllocationValidator;
    }

    @Override
    public Mono<Boolean> validate(UpdateLeaveAllocationDTO data) {
        return Mono
                .from(leaveAllocationRepository
                        .existsById(data.getId())
                        .flatMap(exists -> !exists ? Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request allocation does not exist")) : Mono.just(true)))
                .map(v -> LeaveAllocationMapper.INSTANCE.copyFromUpdateDTO(data))
                .flatMap(createDTO -> createLeaveAllocationValidator.validate(createDTO));
    }
}