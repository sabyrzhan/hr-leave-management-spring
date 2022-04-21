package kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.validators;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.Validator;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.CreateLeaveTypeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class CreateLeaveTypeValidator implements Validator<CreateLeaveTypeDTO> {

    @Override
    public Mono<Boolean> validate(CreateLeaveTypeDTO data) {
        return Mono.defer(() -> {
            if (StringUtils.isBlank(data.getName())) {
                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required"));
            }

            if (data.getName().length() > 50) {
                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name should not exceed 50 characters."));
            }

            if (data.getDefaultDays() <= 0 || data.getDefaultDays() > 100) {
                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Default days is required and length must be between 1 and 100."));
            }

            return Mono.just(true);
        });
    }
}
