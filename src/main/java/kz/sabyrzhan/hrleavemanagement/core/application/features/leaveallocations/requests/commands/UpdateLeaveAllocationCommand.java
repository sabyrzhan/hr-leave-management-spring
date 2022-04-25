package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.UpdateLeaveAllocationDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateLeaveAllocationCommand {
    private final UpdateLeaveAllocationDTO leaveAllocation;
}
