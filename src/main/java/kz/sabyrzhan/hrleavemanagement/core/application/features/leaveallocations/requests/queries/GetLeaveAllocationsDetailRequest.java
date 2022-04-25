package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.queries;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetLeaveAllocationsDetailRequest {
    private final int id;
}
