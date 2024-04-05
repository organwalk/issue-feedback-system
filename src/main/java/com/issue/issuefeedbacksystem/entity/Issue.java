package com.issue.issuefeedbacksystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {
    private Integer issueId;
    private Integer userId;
    private Integer typeId;
    private String title;
    private String desc;
    private Integer statusId;
    private String createDatetime;
}
