package kz.sabyrzhan.hrleavemanagement.api.handlers;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.CreateLeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.LeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.UpdateLeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands.DeleteItemCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands.CreateLeaveAllocationCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands.DeleteLeaveAllocationCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.commands.UpdateLeaveAllocationCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.queries.GetLeaveAllocationDetailRequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.handlers.queries.GetLeaveAllocationListRequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands.CreateLeaveAllocationCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands.UpdateLeaveAllocationCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.queries.GetLeaveAllocationsDetailRequest;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.queries.GetLeaveAllocationsListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LeaveAllocationHandlers {
    private final CreateLeaveAllocationCommandHandler createLeaveAllocationCommandHandler;
    private final DeleteLeaveAllocationCommandHandler deleteLeaveAllocationCommandHandler;
    private final UpdateLeaveAllocationCommandHandler updateLeaveAllocationCommandHandler;
    private final GetLeaveAllocationDetailRequestHandler leaveAllocationDetailRequestHandler;
    private final GetLeaveAllocationListRequestHandler leaveAllocationListRequestHandler;

    public Mono<ServerResponse> createLeaveAllocation(ServerRequest serverRequest) {
        return Mono
                .from(serverRequest.bodyToMono(CreateLeaveAllocationDTO.class))
                .flatMap(createDTO ->
                        ServerResponse
                                .ok()
                                .body(createLeaveAllocationCommandHandler.handle(new CreateLeaveAllocationCommand(createDTO)), LeaveAllocationDTO.class)
                );
    }

    public Mono<ServerResponse> deleteLeaveAllocation(ServerRequest serverRequest) {
        return Mono
                .fromSupplier(() -> new DeleteItemCommand(Integer.parseInt(serverRequest.pathVariable("id"))))
                .flatMap(deleteItemCommand -> ServerResponse.noContent().build(deleteLeaveAllocationCommandHandler.handle(deleteItemCommand)));
    }

    public Mono<ServerResponse> updateLeaveAllocation(ServerRequest serverRequest) {
        return Mono
                .from(serverRequest.bodyToMono(UpdateLeaveAllocationDTO.class))
                .flatMap(updateDTO ->
                        ServerResponse
                                .ok()
                                .body(updateLeaveAllocationCommandHandler.handle(new UpdateLeaveAllocationCommand(updateDTO)), LeaveAllocationDTO.class)
                );
    }

    public Mono<ServerResponse> getLeaveAllocationDetails(ServerRequest serverRequest) {
        var query = new GetLeaveAllocationsDetailRequest(Integer.valueOf(serverRequest.pathVariable("id")));
        return ServerResponse.ok().body(leaveAllocationDetailRequestHandler.handle(query), LeaveAllocationDTO.class);
    }

    public Mono<ServerResponse> getLeaveAllocationsList(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(leaveAllocationListRequestHandler.handle(new GetLeaveAllocationsListRequest()), List.class);
    }
}
