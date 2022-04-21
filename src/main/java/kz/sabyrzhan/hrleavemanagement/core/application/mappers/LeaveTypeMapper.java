package kz.sabyrzhan.hrleavemanagement.core.application.mappers;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leavetype.LeaveTypeDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveTypeMapper {
    LeaveTypeMapper INSTANCE = Mappers.getMapper(LeaveTypeMapper.class);

    void updateLeaveType(LeaveTypeDTO leaveTypeDTO, @MappingTarget LeaveType existingLeaveType);
}
