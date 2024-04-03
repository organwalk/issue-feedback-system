package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;


public interface UserService {
    MsgResult register(UserRegistrationDTO userRegistrationDTO);
    CommonResult<?> login(UserLoginDTO userLoginDTO);
    PagedResult<?> getPendingUserList(Integer size, Integer offset);
}
