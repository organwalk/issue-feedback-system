package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.EvaluationDTO;
import com.issue.issuefeedbacksystem.dto.IssueDTO;
import com.issue.issuefeedbacksystem.dto.IssueReassignDTO;
import com.issue.issuefeedbacksystem.dto.ReplyDTO;
import com.issue.issuefeedbacksystem.service.IssueService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issue")
@RequiredArgsConstructor
@Validated
public class IssueController {
    private final IssueService issueService;

    @PostMapping("/save")
    public MsgResult addIssue(@RequestBody IssueDTO issueDTO) {
        return issueService.addIssue(issueDTO);
    }

    @PostMapping("/reply")
    public MsgResult reply(@Validated @RequestBody ReplyDTO replyDTO) {
        return issueService.reply(replyDTO);
    }

    @PostMapping("/evaluate")
    public MsgResult evaluate(@Validated @RequestBody EvaluationDTO evaluationDTO) {
        return issueService.evaluate(evaluationDTO);
    }

    @GetMapping("details/{issueId}")
    public CommonResult<?> getIssueDetails(@Valid
                                            @PathVariable("issueId")
                                            @NotNull(message = "意见id不能为空")
                                            Integer issueId) {
        return issueService.getIssueDetails(issueId);
    }

    @GetMapping("list/status")
    public PagedResult<?> listByStatus(@Valid
                                        @Min(value = 1, message = "状态值不能小于1")
                                        @Max(value = 4, message = "状态值不能大于4")
                                        Integer issueStatus,
                                        @Min(value = 1, message = "size不能小于1")
                                        @RequestParam(value = "size") Integer size,
                                        @Min(value = 0, message = "offset不能小于0")
                                        @RequestParam(value = "offset") Integer offset) {
        return issueService.listByStatus(issueStatus, size, offset);
    }

    @PutMapping("/fallback/{id}")
    public MsgResult fallback(@PathVariable Integer id) {
        return issueService.fallback(id);
    }

    @PutMapping("/reassign")
    public MsgResult reassign(@RequestBody IssueReassignDTO issueReassignDTO) {
        return issueService.reassign(issueReassignDTO);
    }

    @PutMapping("/archive/{id}")
    public MsgResult archive(@PathVariable Integer id) {
        return issueService.archive(id);
    }
}
