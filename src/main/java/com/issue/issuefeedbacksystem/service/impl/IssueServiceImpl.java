package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.dao.IssueDAO;
import com.issue.issuefeedbacksystem.dto.IssueReassignDTO;
import com.issue.issuefeedbacksystem.entity.Issue;
import com.issue.issuefeedbacksystem.entity.IssueStatus;
import com.issue.issuefeedbacksystem.service.IssueService;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.issue.issuefeedbacksystem.constant.IssueStatusConstant.ARCHIVED;
import static com.issue.issuefeedbacksystem.constant.IssueStatusConstant.FALLBACK;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueServiceImpl implements IssueService
{

    private final IssueDAO issueDAO;

    @Override
    public MsgResult fallback(Integer id)
    {
        if (isArchived(id)) return MsgResult.Archived();
        Issue issue = Issue.builder()
                .issueId(id)
                .statusId(FALLBACK)
                .build();
        return issueDAO.updateIssue(issue) > 0 ? MsgResult.success("回退成功") : MsgResult.fail("回退失败");
    }

    @Override
    public MsgResult reassign(IssueReassignDTO issueReassignDTO)
    {
        if (isArchived(issueReassignDTO.getIssueId())) return MsgResult.Archived();
        Issue issue = new Issue();
        BeanUtils.copyProperties(issueReassignDTO, issue);
        return issueDAO.updateIssue(issue) > 0 ? MsgResult.success("分类重派成功") : MsgResult.fail("分类重派失败");
    }

    @Override
    public MsgResult Archive(Integer id)
    {
        if (isArchived(id)) return MsgResult.Archived();
        Issue issue = Issue.builder()
                .issueId(id)
                .statusId(ARCHIVED)
                .build();
        return issueDAO.updateIssue(issue) > 0 ? MsgResult.success("归档成功") : MsgResult.fail("归档失败");
    }


    private boolean isArchived(Integer id)
    {
        IssueStatus issueStatus = issueDAO.getIssueStatus(id);
        return issueStatus.getStatusName().equals("已归档");
    }

}
