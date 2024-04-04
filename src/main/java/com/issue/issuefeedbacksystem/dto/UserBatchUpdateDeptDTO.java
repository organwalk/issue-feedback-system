package com.issue.issuefeedbacksystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserBatchUpdateDeptDTO {
    private List<Integer> userIdList;
    private Integer deptId;
}
