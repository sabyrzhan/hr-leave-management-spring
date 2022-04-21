package kz.sabyrzhan.hrleavemanagement.core.application.mappers;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.ChangeLeaveRequestApprovalDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.LeaveRequestDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.LeaveRequestListDTO;
import kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest.UpdateLeaveRequestDTO;
import kz.sabyrzhan.hrleavemanagement.core.domain.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveRequestMapper {
    LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);

    LeaveRequestListDTO convertToListDTO(LeaveRequest from);
    LeaveRequestDTO convertToDTO(LeaveRequest from);
    LeaveRequest createFromDTO(LeaveRequestDTO from);
    void updateLeaveRequest(UpdateLeaveRequestDTO source, LeaveRequest target);
    void updateChangeApproval(ChangeLeaveRequestApprovalDTO source, LeaveRequest target);
}
