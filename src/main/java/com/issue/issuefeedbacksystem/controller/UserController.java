package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.service.UserService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user")
    public MsgResult register(@Validated @RequestBody UserRegistrationDTO userRegistrationDTO){
        return userService.register(userRegistrationDTO);
    }

    @PostMapping("/auth")
    public CommonResult<?> login(@Validated @RequestBody UserLoginDTO userLoginDTO){
        return userService.login(userLoginDTO);
    }


}
