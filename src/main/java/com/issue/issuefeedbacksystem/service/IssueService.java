package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.EvaluationDTO;
import com.issue.issuefeedbacksystem.dto.IssueDTO;
import com.issue.issuefeedbacksystem.dto.ReplyDTO;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import org.apache.ibatis.annotations.Insert;

public interface IssueService {
    MsgResult addIssue(IssueDTO issueDTO);
    MsgResult reply(ReplyDTO replyDTO);

    MsgResult evaluate(EvaluationDTO evaluationDTO);

    CommonResult<?> getIssueDetails(Integer issueId);

    PagedResult<?> listByStatus(Integer issueStatus, Integer size, Integer offset);
}
