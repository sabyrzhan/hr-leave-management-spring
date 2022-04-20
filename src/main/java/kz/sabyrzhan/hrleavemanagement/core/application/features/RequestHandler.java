package kz.sabyrzhan.hrleavemanagement.core.application.features;

import reactor.core.publisher.Mono;

public interface RequestHandler<T,R> {
    Mono<R> handle(T request);
}
