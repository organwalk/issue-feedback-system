package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.PendingUserRoleDTO;
import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.service.AuthService;
import com.issue.issuefeedbacksystem.service.UserService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;

    @PostMapping("/user")
    public MsgResult register(@Validated @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return authService.register(userRegistrationDTO);
    }

    @PostMapping("/token")
    public CommonResult<?> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return authService.login(userLoginDTO);
    }
}
