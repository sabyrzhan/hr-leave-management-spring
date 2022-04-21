package kz.sabyrzhan.hrleavemanagement.core.application.features.leaveallocations.requests.commands;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.CreateLeaveAllocationDTO;
import lombok.Data;

@Data
public class CreateLeaveAllocationCommand {
    private CreateLeaveAllocationDTO leaveAllocation;
}
