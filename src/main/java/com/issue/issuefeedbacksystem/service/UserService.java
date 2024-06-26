package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.*;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;

import java.util.List;


public interface UserService {

    PagedResult<?> getPendingUserList(Integer size, Integer offset);
    MsgResult setUserRole(UserBatchUpdateRoleDTO userBatchUpdateRoleDTO);
    PagedResult<?> getUserList(Integer size, Integer offset);
    MsgResult updateUser(UserUpdateDTO userUpdateDTO);
    MsgResult batchUpdateUserDept(UserBatchUpdateDeptDTO userBatchUpdateDeptDTO);
    MsgResult deleteUser(List<Integer> userIdList);
    PagedResult<?> getUserListByRole(Integer size, Integer offset, Integer roleId);
    PagedResult<?> getUserListByDept(Integer size, Integer offset, Integer deptId);
    PagedResult<?> getUserListByPhone(Integer size, Integer offset, String phone);

}
