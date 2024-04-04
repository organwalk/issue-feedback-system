package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;

import java.util.List;

public interface RoleService {
    CommonResult<?> getRoleList();
    MsgResult addRole(String roleName);
    MsgResult updateRole(String roleName,Integer roleId);
    MsgResult deleteRole(List<Integer> roleIdList);
}
