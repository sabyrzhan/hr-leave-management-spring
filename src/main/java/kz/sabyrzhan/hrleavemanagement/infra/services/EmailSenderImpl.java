package kz.sabyrzhan.hrleavemanagement.infra.services;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.services.EmailSender;
import kz.sabyrzhan.hrleavemanagement.core.application.models.Email;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Mono<Boolean> sendEmail(Email email) {
        return Mono
                .fromSupplier(() -> {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(email.getTo());

                    msg.setSubject(email.getSubject());
                    msg.setText(email.getBody());

                    javaMailSender.send(msg);

                    return true;
                })
                .onErrorResume(t -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", t)));
    }
}
