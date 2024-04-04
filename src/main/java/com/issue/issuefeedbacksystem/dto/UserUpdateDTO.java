package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @NotNull(message = "userId不能为空")
    private Integer userId;
    private String username;
    private String passwordHash;
    private Integer roleId;
    private Integer deptId;
    private String phone;
}
