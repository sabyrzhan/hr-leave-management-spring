package kz.sabyrzhan.hrleavemanagement.core.application.contracts.services;

import kz.sabyrzhan.hrleavemanagement.core.application.models.Email;
import reactor.core.publisher.Mono;

public interface EmailSender {
    Mono<Boolean> sendEmail(Email email);
}
