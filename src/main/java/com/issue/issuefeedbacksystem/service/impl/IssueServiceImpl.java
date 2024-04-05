package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.bo.IssueBO;
import com.issue.issuefeedbacksystem.bo.IssueDetailsBO;
import com.issue.issuefeedbacksystem.context.BaseContext;
import com.issue.issuefeedbacksystem.dao.EvaluationDAO;
import com.issue.issuefeedbacksystem.dao.IssueDAO;
import com.issue.issuefeedbacksystem.dao.ReplyDAO;
import com.issue.issuefeedbacksystem.dto.EvaluationDTO;
import com.issue.issuefeedbacksystem.dto.IssueDTO;
import com.issue.issuefeedbacksystem.dto.IssueReassignDTO;
import com.issue.issuefeedbacksystem.dto.ReplyDTO;
import com.issue.issuefeedbacksystem.entity.Evaluation;
import com.issue.issuefeedbacksystem.entity.Issue;
import com.issue.issuefeedbacksystem.entity.Reply;
import com.issue.issuefeedbacksystem.service.IssueService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import com.issue.issuefeedbacksystem.constant.IssueStatusConstant;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {
    private final IssueDAO issueDAO;
    private final ReplyDAO replyDAO;
    private final EvaluationDAO evaluationDAO;

    @Override
    public MsgResult addIssue(IssueDTO issueDTO) {
        Issue issue = new Issue();
        BeanUtils.copyProperties(issueDTO, issue);
        Integer userId = BaseContext.getCurrentId();
        issue.setUserId(userId);
        issue.setStatusId(IssueStatusConstant.WAIT_FOR_REPLY);
        int row = issueDAO.insertIssue(issue);
        return row > 0 ? MsgResult.success("意见填写成功") : MsgResult.fail("意见填写失败");
    }


    @Override
    public MsgResult reply(ReplyDTO replyDTO) {
        Integer issueId = replyDTO.getIssueId();
        Integer issueStatus = issueDAO.selectStatusById(issueId);
        if (issueStatus == 4) {
            return MsgResult.success("已归档的意见无法进行回复");
        }
        Reply reply = new Reply();
        BeanUtils.copyProperties(replyDTO, reply);
        reply.setUserId(BaseContext.getCurrentId());
        System.out.println(reply);
        int row = replyDAO.insertReply(reply);
        if (Objects.equals(issueStatus, IssueStatusConstant.WAIT_FOR_REPLY)) {
            row += issueDAO.updateIssueStatusById(issueId, IssueStatusConstant.REPLIED);
        }
        return row > 0 ? MsgResult.success("意见回复成功") : MsgResult.fail("意见回复失败");
    }

    @Override
    public MsgResult evaluate(EvaluationDTO evaluationDTO) {
        Integer issueId = evaluationDTO.getIssueId();
        Integer issueStatus = issueDAO.selectStatusById(issueId);
        if (Objects.equals(issueStatus, IssueStatusConstant.WAIT_FOR_REPLY)) {
            return MsgResult.success("未回复的意见不能进行评价");
        } else if (Objects.equals(issueStatus, IssueStatusConstant.ARCHIVED)) {
            return MsgResult.success("已归档的意见无法进行评价");
        }
        int row = evaluationDAO.insertEvaluation(evaluationDTO);
        row += issueDAO.updateIssueStatusById(issueId, IssueStatusConstant.ARCHIVED);
        archive(issueId);
        return row > 0 ? MsgResult.success("意见评价成功") : MsgResult.fail("意见评价失败");
    }

    @Override
    public CommonResult<?> getIssueDetails(Integer issueId) {
        IssueDetailsBO issueDetailsBO = issueDAO.selectById(issueId);
        List<Reply> replies = replyDAO.selectReplyByIssueId(issueId);
        Evaluation evaluation = evaluationDAO.selectByIssueId(issueId);
        issueDetailsBO.setReplies(replies);
        issueDetailsBO.setEvaluation(evaluation);
        return CommonResult.success("查看意见成功", issueDetailsBO);
    }

    @Override
    public PagedResult<?> listByStatus(Integer issueStatus, Integer size, Integer offset) {
        Integer total = issueDAO.countNumsByStatus(issueStatus);
        if (total == 0) return PagedResult.fail("当前状态的意见列表为空");
        List<IssueBO> issueList = issueDAO.selectIssueListByStatus(issueStatus, size, offset);
        return !issueList.isEmpty()
                ? PagedResult.success("成功获取当前状态意见列表", total, issueList)
                : PagedResult.fail("当前活动的状态意见列表为空");
    }

    @Override
    public MsgResult fallback(Integer id)
    {
        if (isArchived(id)) return MsgResult.Archived();
        Issue issue = Issue.builder()
                .issueId(id)
                .statusId(IssueStatusConstant.FALLBACK)
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
    public MsgResult archive(Integer id)
    {
        if (isArchived(id)) return MsgResult.Archived();
        Issue issue = Issue.builder()
                .issueId(id)
                .statusId(IssueStatusConstant.ARCHIVED)
                .build();
        return issueDAO.updateIssue(issue) > 0 ? MsgResult.success("归档成功") : MsgResult.fail("归档失败");
    }


    private boolean isArchived(Integer id)
    {
        Integer issueStatus = issueDAO.selectStatusById(id);
        return Objects.equals(issueStatus,IssueStatusConstant.ARCHIVED);
    }
}
