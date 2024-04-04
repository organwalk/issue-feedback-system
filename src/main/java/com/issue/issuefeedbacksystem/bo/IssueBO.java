package com.issue.issuefeedbacksystem.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueBO {
    private Integer issueId;
    private String title;
    private String desc;
    private Integer typeId;
    private String typeName;
}
