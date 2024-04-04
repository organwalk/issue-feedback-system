package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvaluationDTO {
    @NotNull(message = "意见id不能为空")
    private Integer issueId;
    @Min(value = 1,message = "评分不能小于1")
    @Max(value = 5,message = "评分不能大于5")
    private Integer rating;
    private String comment;
}
