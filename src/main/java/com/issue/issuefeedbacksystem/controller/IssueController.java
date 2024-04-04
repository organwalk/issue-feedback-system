package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.IssueReassignDTO;
import com.issue.issuefeedbacksystem.service.IssueService;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issue")
@RequiredArgsConstructor
public class IssueController
{
    private final IssueService issueService;

    @PutMapping ("/fallback/{id}")
    public MsgResult fallback(@PathVariable Integer id)
    {
        return issueService.fallback(id);
    }

    @PutMapping("/reassign")
    public MsgResult reassign(@RequestBody IssueReassignDTO issueReassignDTO)
    {
        return issueService.reassign(issueReassignDTO);
    }
    @PutMapping ("/archive/{id}")
    public MsgResult archive(@PathVariable Integer id)
    {
        return issueService.archive(id);
    }
}
