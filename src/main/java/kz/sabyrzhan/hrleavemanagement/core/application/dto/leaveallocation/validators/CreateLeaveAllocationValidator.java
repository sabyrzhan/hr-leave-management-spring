package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.validators;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.LeaveTypeRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.Validator;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.CreateLeaveAllocationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class CreateLeaveAllocationValidator implements Validator<CreateLeaveAllocationDTO> {
    private final LeaveTypeRepository leaveTypeRepository;

    public CreateLeaveAllocationValidator(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public Mono<Boolean> validate(CreateLeaveAllocationDTO data) {
        return Mono.
                defer(() -> {
                   if (data.getNumberOfDays() < 1) {
                       return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number of days must not be less than 1"));
                   }

                   if (data.getNumberOfDays() < 0) {
                       return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number of days is required"));
                   }

                   if (data.getPeriod() < LocalDate.now().getYear()) {
                       return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Period should be year and equal or greater than current year"));
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
