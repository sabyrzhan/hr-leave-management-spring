package kz.sabyrzhan.hrleavemanagement.core.application.mappers;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.*;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveRequestMapper {
    LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);

    LeaveRequestListDTO convertToListDTO(LeaveRequest from);
    LeaveRequestDTO convertToDTO(LeaveRequest from);
    LeaveRequest createFromDTO(LeaveRequestDTO from);
    LeaveRequest createFromCreateDTO(CreateLeaveRequestDTO from);
    CreateLeaveRequestDTO copyFromUpdateRequestDTO(UpdateLeaveRequestDTO from);
    void updateLeaveRequest(UpdateLeaveRequestDTO source, @MappingTarget LeaveRequest target);
    void updateChangeApproval(ChangeLeaveRequestApprovalDTO source, @MappingTarget LeaveRequest target);
}
