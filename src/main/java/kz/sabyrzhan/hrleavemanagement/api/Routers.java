package kz.sabyrzhan.hrleavemanagement.api;

import kz.sabyrzhan.hrleavemanagement.api.handlers.LeaveTypeHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Routers {
    private final LeaveTypeHandlers leaveTypeHandlers;

    public Routers(LeaveTypeHandlers leaveTypeHandlers) {
        this.leaveTypeHandlers = leaveTypeHandlers;
    }

    @Bean
    public RouterFunction<ServerResponse> leaveTypesRouters() {
        return RouterFunctions
                .route(RequestPredicates.GET("/api/leaveTypes"), leaveTypeHandlers::getLeaveTypes)
                .andRoute(RequestPredicates.GET("/api/leaveTypes/{id}"), leaveTypeHandlers::getLeaveTypeById)
                .andRoute(RequestPredicates.POST("/api/leaveTypes"), leaveTypeHandlers::createLeaveType)
                .andRoute(RequestPredicates.PUT("/api/leaveTypes"), leaveTypeHandlers::updateLeaveType)
                .andRoute(RequestPredicates.DELETE("/api/leaveTypes/{id}"), leaveTypeHandlers::deleteLeaveType);
    }
}
