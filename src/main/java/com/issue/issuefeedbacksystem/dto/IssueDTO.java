package com.issue.issuefeedbacksystem.dto;

import lombok.Data;

@Data
public class IssueDTO {
    private String title;
    private String desc;
    private Integer typeId;
}
