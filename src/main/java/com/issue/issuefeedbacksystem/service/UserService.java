package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.PendingUserRoleDTO;
import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;

import java.util.List;


public interface UserService {
    MsgResult register(UserRegistrationDTO userRegistrationDTO);
    CommonResult<?> login(UserLoginDTO userLoginDTO);
    PagedResult<?> getPendingUserList(Integer size, Integer offset);
    MsgResult setUserRole(List<PendingUserRoleDTO> pendingUserRoleDTOList);
}
