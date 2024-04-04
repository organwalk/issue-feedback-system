package com.issue.issuefeedbacksystem.entity;

import lombok.Data;

@Data
public class Reply {
    private Integer replyId;
    private Integer issueId;
    private Integer userId;
    private Integer content;
    private String createDatetime;
}
