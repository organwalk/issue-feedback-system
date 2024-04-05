package com.issue.issuefeedbacksystem.entity;

import lombok.Data;

@Data
public class Reply {
    private Integer replyId;
    private Integer issueId;
    private Integer userId;
    private String content;
    private String createDatetime;
}
