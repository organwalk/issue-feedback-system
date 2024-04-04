package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.dao.IssueDAO;
import com.issue.issuefeedbacksystem.dto.IssueReassignDTO;
import com.issue.issuefeedbacksystem.service.IssueService;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueServiceImpl implements IssueService
{

    private final IssueDAO issueDAO;
    @Override
    public MsgResult fallback(String id)
    {
        return null;
    }

    @Override
    public MsgResult reassign(IssueReassignDTO issueReassignDTO)
    {
        return null;
    }
}
