package kz.sabyrzhan.hrleavemanagement.core.application.mappers;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.LeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaveallocation.UpdateLeaveAllocationDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveAllocation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveAllocationMapper {
    LeaveAllocationMapper INSTANCE = Mappers.getMapper(LeaveAllocationMapper.class);

    LeaveAllocationDTO convertToDTO(LeaveAllocation from);
    LeaveAllocation createFromDTO(LeaveAllocationDTO from);

    void updateLeaveAllocation(UpdateLeaveAllocationDTO source, @MappingTarget LeaveAllocation target);
}
