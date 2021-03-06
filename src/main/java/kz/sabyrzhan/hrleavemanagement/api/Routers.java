package kz.sabyrzhan.hrleavemanagement.api;

import kz.sabyrzhan.hrleavemanagement.api.handlers.LeaveAllocationHandlers;
import kz.sabyrzhan.hrleavemanagement.api.handlers.LeaveRequestHandlers;
import kz.sabyrzhan.hrleavemanagement.api.handlers.LeaveTypeHandlers;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class Routers {
    private final LeaveTypeHandlers leaveTypeHandlers;
    private final LeaveAllocationHandlers leaveAllocationHandlers;
    private final LeaveRequestHandlers leaveRequestHandlers;

    @Bean
    public RouterFunction<ServerResponse> routerFunctions() {
        return RouterFunctions.route()
                .path("/api/leaveTypes", builder -> builder
                        .GET("", leaveTypeHandlers::getLeaveTypes)
                        .GET("/{id}", leaveTypeHandlers::getLeaveTypeById)
                        .POST("", leaveTypeHandlers::createLeaveType)
                        .PUT("", leaveTypeHandlers::updateLeaveType)
                        .DELETE("/{id}", leaveTypeHandlers::deleteLeaveType))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> leaveAllocationRouters() {
        return RouterFunctions.route()
                .path("/api/leaveAllocations", builder -> builder
                        .POST("", leaveAllocationHandlers::createLeaveAllocation)
                        .DELETE("/{id}", leaveAllocationHandlers::deleteLeaveAllocation)
                        .PUT("/{id}", leaveAllocationHandlers::updateLeaveAllocation)
                        .GET("", leaveAllocationHandlers::getLeaveAllocationsList)
                        .GET("/{id}", leaveAllocationHandlers::getLeaveAllocationDetails))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> leaveRequestRouters() {
        return RouterFunctions.route()
                .path("/api/leaveRequests", builder -> builder
                        .PUT("/{id}/changeApproval", leaveRequestHandlers::changeApprovalStatus)
                        .POST("", leaveRequestHandlers::createLeaveRequest)
                        .DELETE("/{id}", leaveRequestHandlers::deleteLeaveRequest)
                        .PUT("/{id}", leaveRequestHandlers::updateLeaveRequest)
                        .GET("/{id}", leaveRequestHandlers::getLeaveRequestDetails)
                        .GET("", leaveRequestHandlers::getLeaveRequestList))
                .build();
    }
}
