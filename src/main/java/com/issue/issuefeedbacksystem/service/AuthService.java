package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;

public interface AuthService {
    MsgResult register(UserRegistrationDTO userRegistrationDTO);
    CommonResult<?> login(UserLoginDTO userLoginDTO);
}
