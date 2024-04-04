package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.service.UserService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public MsgResult register(@Validated @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.register(userRegistrationDTO);
    }

    @PostMapping("/auth")
    public CommonResult<?> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @GetMapping("/pending-users/")
    public PagedResult<?> getPendingUserList(@Valid
                                             @Min(value = 1, message = "size不能小于1")
                                             @RequestParam(value = "size") Integer size,
                                             @Min(value = 0, message = "offset不能小于0")
                                             @RequestParam(value = "offset") Integer offset) {
        return userService.getPendingUserList(size, offset);
    }


}
