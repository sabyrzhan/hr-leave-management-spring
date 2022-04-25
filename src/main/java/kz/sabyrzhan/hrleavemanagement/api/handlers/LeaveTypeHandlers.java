package kz.sabyrzhan.hrleavemanagement.api.handlers;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.CreateLeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands.DeleteItemCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands.CreateLeaveTypeCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands.DeleteLeaveTypeCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.commands.UpdateLeaveTypeCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.queries.GetLeaveTypeDetailRequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.handlers.queries.GetLeaveTypeListRequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands.CreateLeaveTypeCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.commands.UpdateLeaveTypeCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.queries.GetLeaveTypeDetailRequest;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leavetypes.requests.queries.GetLeaveTypeListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LeaveTypeHandlers {
    private final GetLeaveTypeListRequestHandler leaveTypesRequestHandler;
    private final GetLeaveTypeDetailRequestHandler leaveTypeDetailRequestHandler;
    private final CreateLeaveTypeCommandHandler createLeaveTypeCommandHandler;
    private final UpdateLeaveTypeCommandHandler updateLeaveTypeCommandHandler;
    private final DeleteLeaveTypeCommandHandler deleteLeaveTypeCommandHandler;

    public Mono<ServerResponse> getLeaveTypes(ServerRequest serverRequest) {
        return ServerResponse.ok().body(leaveTypesRequestHandler.handle(new GetLeaveTypeListRequest()), List.class);
    }

    public Mono<ServerResponse> getLeaveTypeById(ServerRequest serverRequest) {
        var request = new GetLeaveTypeDetailRequest(Integer.parseInt(serverRequest.pathVariable("id")));
        return ServerResponse.ok().body(leaveTypeDetailRequestHandler.handle(request), LeaveTypeDTO.class);
    }

    public Mono<ServerResponse> createLeaveType(ServerRequest serverRequest) {
        return Mono
                .from(serverRequest.bodyToMono(CreateLeaveTypeDTO.class))
                .flatMap(createDTO -> ServerResponse.ok().body(createLeaveTypeCommandHandler.handle(new CreateLeaveTypeCommand(createDTO)), LeaveTypeDTO.class));
    }

    public Mono<ServerResponse> updateLeaveType(ServerRequest serverRequest) {
        return Mono
                .from(serverRequest.bodyToMono(LeaveTypeDTO.class))
                .flatMap(updateDTO -> ServerResponse.ok().body(updateLeaveTypeCommandHandler.handle(new UpdateLeaveTypeCommand(updateDTO)), LeaveTypeDTO.class));
    }

    public Mono<ServerResponse> deleteLeaveType(ServerRequest serverRequest) {
        return Mono
                .fromSupplier(() -> {
                    var id = Integer.parseInt(serverRequest.pathVariable("id"));
                    return new DeleteItemCommand(id);
                })
                .flatMap(deleteCommand -> ServerResponse.noContent().build(deleteLeaveTypeCommandHandler.handle(deleteCommand)));
    }
}
