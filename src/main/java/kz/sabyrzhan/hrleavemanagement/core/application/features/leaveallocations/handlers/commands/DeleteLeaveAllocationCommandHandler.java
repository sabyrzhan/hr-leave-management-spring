package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.contracts.persistence.LeaveAllocationRepository;
import kz.sabyrzhan.hrleavemanagement.core.application.features.RequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands.DeleteItemCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteLeaveAllocationCommandHandler implements RequestHandler<DeleteItemCommand, Void> {
    private final LeaveAllocationRepository leaveAllocationRepository;

    public DeleteLeaveAllocationCommandHandler(LeaveAllocationRepository repository) {
        this.leaveAllocationRepository = repository;
    }

    @Override
    public Mono<Void> handle(DeleteItemCommand request) {
        return leaveAllocationRepository.deleteById(request.getId());
    }
}
