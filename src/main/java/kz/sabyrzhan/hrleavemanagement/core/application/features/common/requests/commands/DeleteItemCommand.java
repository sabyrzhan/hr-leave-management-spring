package kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeleteItemCommand {
    private final int id;
}
