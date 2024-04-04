package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.IssueReassignDTO;
import com.issue.issuefeedbacksystem.vo.MsgResult;

public interface IssueService
{
    MsgResult fallback(String id);
    MsgResult reassign(IssueReassignDTO issueReassignDTO);

}
