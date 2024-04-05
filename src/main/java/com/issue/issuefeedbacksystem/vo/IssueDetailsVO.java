package com.issue.issuefeedbacksystem.vo;

import com.issue.issuefeedbacksystem.entity.Evaluation;
import com.issue.issuefeedbacksystem.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueDetailsVO
{
    private String title;
    private String desc;
    private Integer typeId;
    private String typeName;
    private Integer statusId;
    private String statusName;
    private Evaluation evaluation;
    private List<Reply> replies;
}
