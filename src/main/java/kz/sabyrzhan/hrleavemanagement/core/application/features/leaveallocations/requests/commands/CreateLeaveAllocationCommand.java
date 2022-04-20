package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.LeaveAllocationDTO;
import lombok.Data;

@Data
public class CreateLeaveAllocationCommand {
    private LeaveAllocationDTO leaveAllocation;
}
