package com.issue.issuefeedbacksystem.entity;

import lombok.Data;

@Data
public class History {
    private Integer historyId;
    private Integer issueId;
    private Integer userId;
    private Integer deptId;
    private String createDatetime;
}
