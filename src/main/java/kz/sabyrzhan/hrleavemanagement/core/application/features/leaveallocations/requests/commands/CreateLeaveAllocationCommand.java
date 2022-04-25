package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.CreateLeaveAllocationDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateLeaveAllocationCommand {
    private final CreateLeaveAllocationDTO leaveAllocation;
}
