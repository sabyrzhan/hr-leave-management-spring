package kz.sabyrzhan.hrleavemanagement.core.application.dto.leaverequest;

import kz.sabyrzhan.hrleavemanagement.core.application.dto.common.BaseDTO;
import lombok.Data;

@Data
public class ChangeLeaveRequestApprovalDTO extends BaseDTO {
    private boolean approved;
}
