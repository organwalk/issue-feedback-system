package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReplyDTO {
    @NotNull(message = "issueId不能为空")
    private Integer issueId;
    private String content;
}
