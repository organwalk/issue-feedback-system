package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
public class IssueCategoryBatchUpdateDeptDTO {
    private List<Integer> typeIdList;
    @Min(value = 1, message = "deptId不能小于1")
    private Integer deptId;
}
