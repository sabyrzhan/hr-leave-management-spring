package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.UpdateLeaveAllocationDTO;
import lombok.Data;

@Data
public class UpdateLeaveAllocationCommand {
    private UpdateLeaveAllocationDTO leaveAllocation;
}
