package kz.sabyrzhan.hrleavemanagement.core.application.dto.common;

import reactor.core.publisher.Mono;

public interface Validator<T> {
    Mono<Boolean> validate(T data);
}
