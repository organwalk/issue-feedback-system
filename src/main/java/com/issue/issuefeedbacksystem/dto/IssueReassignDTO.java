package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IssueReassignDTO
{
    @NotBlank(message = "问题不能为空")
    private String id;
    @NotBlank(message = "问题分类不能为空")
    private String IssueCategoryId;
}
