package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.IssueReassignDTO;
import com.issue.issuefeedbacksystem.vo.MsgResult;

public interface IssueService
{
    MsgResult fallback(Integer id);
    MsgResult reassign(IssueReassignDTO issueReassignDTO);

    MsgResult Archive(Integer id);


}
