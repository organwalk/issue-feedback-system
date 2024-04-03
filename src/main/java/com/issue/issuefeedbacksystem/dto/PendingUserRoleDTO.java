package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PendingUserRoleDTO {
    @Min(value = 1, message = "userId最小为1")
    private Integer userId;
    @Min(value = 1, message = "roleId最小为1")
    private Integer roleId;
}
