package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IssueReassignDTO
{
    @NotNull(message = "意见id不能为空")
    private Integer issueId;
    @NotNull(message = "问题分类不能为空")
    private Integer typeId;
}
