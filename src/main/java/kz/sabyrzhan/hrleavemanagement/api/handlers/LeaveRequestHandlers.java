package kz.sabyrzhan.hrleavemanagement.api.handlers;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.ChangeLeaveRequestApprovalDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.CreateLeaveRequestDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.LeaveRequestDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.UpdateLeaveRequestDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.features.common.requests.commands.DeleteItemCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands.ChangeLeaveRequestApprovalCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands.CreateLeaveRequestCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands.DeleteLeaveRequestCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.commands.UpdateLeaveRequestCommandHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.queries.GetLeaveRequestDetailRequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.handlers.queries.GetLeaveRequestListRequestHandler;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands.ChangeLeaveRequestApprovalCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands.CreateLeaveRequestCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.commands.UpdateLeaveRequestCommand;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.queries.GetLeaveRequestDetailRequest;
import kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.queries.GetLeaveRequestListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LeaveRequestHandlers {
    private final ChangeLeaveRequestApprovalCommandHandler changeLeaveRequestApprovalCommandHandler;
    private final CreateLeaveRequestCommandHandler createLeaveRequestCommandHandler;
    private final DeleteLeaveRequestCommandHandler deleteLeaveRequestCommandHandler;
    private final UpdateLeaveRequestCommandHandler updateLeaveRequestCommandHandler;
    private final GetLeaveRequestDetailRequestHandler leaveRequestDetailRequestHandler;
    private final GetLeaveRequestListRequestHandler leaveRequestListRequestHandler;

    public Mono<ServerResponse> changeApprovalStatus(ServerRequest serverRequest) {
        return Mono
                .from(serverRequest.bodyToMono(ChangeLeaveRequestApprovalDTO.class))
                .flatMap(changeDTO -> {
                    changeDTO.setId(Integer.parseInt(serverRequest.pathVariable("id")));
                    return ServerResponse
                            .ok()
                            .body(changeLeaveRequestApprovalCommandHandler.handle(new ChangeLeaveRequestApprovalCommand(changeDTO)), LeaveRequestDTO.class);
                });
    }

    public Mono<ServerResponse> createLeaveRequest(ServerRequest serverRequest) {
        return Mono
                .from(serverRequest.bodyToMono(CreateLeaveRequestDTO.class))
                .flatMap(createDTO ->
                        ServerResponse
                                .ok()
                                .body(createLeaveRequestCommandHandler.handle(new CreateLeaveRequestCommand(createDTO)), LeaveRequestDTO.class)
                );
    }

    public Mono<ServerResponse> deleteLeaveRequest(ServerRequest serverRequest) {
        return Mono
                .fromSupplier(() -> new DeleteItemCommand(Integer.parseInt(serverRequest.pathVariable("id"))))
                .flatMap(request -> ServerResponse.noContent().build(deleteLeaveRequestCommandHandler.handle(request)));
    }

    public Mono<ServerResponse> updateLeaveRequest(ServerRequest serverRequest) {
        return Mono
                .from(serverRequest.bodyToMono(UpdateLeaveRequestDTO.class))
                .flatMap(updateDTO -> {
                    updateDTO.setId(Integer.parseInt(serverRequest.pathVariable("id")));
                    return ServerResponse.ok().body(updateLeaveRequestCommandHandler.handle(new UpdateLeaveRequestCommand(updateDTO)), LeaveRequestDTO.class);
                });
    }

    public Mono<ServerResponse> getLeaveRequestDetails(ServerRequest serverRequest) {
        return Mono
                .fromSupplier(() -> new GetLeaveRequestDetailRequest(Integer.parseInt(serverRequest.pathVariable("id"))))
                .flatMap(request -> ServerResponse.ok().body(leaveRequestDetailRequestHandler.handle(request), LeaveRequestDTO.class));
    }

    public Mono<ServerResponse> getLeaveRequestList(ServerRequest serverRequest) {
        return ServerResponse.ok().body(leaveRequestListRequestHandler.handle(new GetLeaveRequestListRequest()), List.class);
    }
}
