package kz.sabyrzhan.hrleavemanagement.core.application.features.leaverequests.requests.queries;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetLeaveRequestDetailRequest {
    private final int id;
}
