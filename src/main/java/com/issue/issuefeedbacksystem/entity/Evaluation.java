package com.issue.issuefeedbacksystem.entity;

import lombok.Data;

@Data
public class Evaluation {
    private Integer evaluationId;
    private Integer issueId;
    private Integer rating;
    private String comment;
    private String createDatetime;
}
