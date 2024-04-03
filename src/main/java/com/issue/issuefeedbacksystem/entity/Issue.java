package com.issue.issuefeedbacksystem.entity;

import lombok.Data;

@Data
public class Issue {
    private Integer issueId;
    private Integer userId;
    private Integer typeId;
    private String title;
    private String desc;
    private Integer statusId;
    private String createDatetime;
}
