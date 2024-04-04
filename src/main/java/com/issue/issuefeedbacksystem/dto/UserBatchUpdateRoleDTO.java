package com.issue.issuefeedbacksystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserBatchUpdateRoleDTO {
    private List<Integer> userIdList;
    private Integer roleId;
}
