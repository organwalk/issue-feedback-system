package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.service.SystemService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
public class SystemController {
    private final SystemService systemService;

    @GetMapping("/roles")
    public CommonResult<?> getRoleList(){
        return systemService.getRoleList();
    }
}
