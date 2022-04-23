package kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteItemCommand {
    private int id;
}
