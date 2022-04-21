package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.validators;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.Validator;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.CreateLeaveRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
public class CreateLeaveRequestValidator implements Validator<CreateLeaveRequestDTO> {
    private final LeaveTypeRepository leaveTypeRepository;

    public CreateLeaveRequestValidator(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<Boolean> validate(CreateLeaveRequestDTO data) {
        return Mono
                .defer(() -> {
                    if (data.getStartDate() == null || data.getEndDate() == null) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start and end dates are required"));
                    }

                    if (data.getStartDate().isBefore(Instant.now())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid start date specified."));
                    }

                    if (data.getStartDate().isAfter(data.getEndDate())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date must be before end date"));
                    }

                    return leaveTypeRepository.existsById(data.getLeaveTypeId());
                })
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid leave type specified"));
                    }

                    return Mono.just(true);
                });
    }
}
