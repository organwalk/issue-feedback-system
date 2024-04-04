package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.EvaluationDTO;
import com.issue.issuefeedbacksystem.dto.IssueDTO;
import com.issue.issuefeedbacksystem.dto.ReplyDTO;
import com.issue.issuefeedbacksystem.service.IssueService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
@Validated
public class IssueController {
    private final IssueService issueService;

    @PostMapping
    private MsgResult addIssue(@RequestBody IssueDTO issueDTO) {
        return issueService.addIssue(issueDTO);
    }

    @PostMapping("/reply")
    private MsgResult reply(@Validated @RequestBody ReplyDTO replyDTO) {
        return issueService.reply(replyDTO);
    }

    @PostMapping("/evaluate")
    private MsgResult evaluate(@Validated @RequestBody EvaluationDTO evaluationDTO) {
        return issueService.evaluate(evaluationDTO);
    }

    @GetMapping("details/{issueId}")
    private CommonResult<?> getIssueDetails(@Valid
                                            @PathVariable("issueId")
                                            @NotNull(message = "意见id不能为空")
                                            Integer issueId) {
        return issueService.getIssueDetails(issueId);
    }
}
