package com.issue.issuefeedbacksystem.bo;

import com.issue.issuefeedbacksystem.entity.Evaluation;
import com.issue.issuefeedbacksystem.entity.Reply;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IssueBO {
    private String title;
    private String desc;
    private Integer typeId;
    private String typeName;
    private Integer statusId;
    private String statusName;
    private Evaluation evaluation;
    private List<Reply> replies;
}
