package kz.sabyrzhan.hrleavemanagement.core.application.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Email {
    private String to;
    private String subject;
    private String body;
}
