package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.bo.PendingUserBO;
import com.issue.issuefeedbacksystem.bo.UserBO;
import com.issue.issuefeedbacksystem.dao.UserDAO;
import com.issue.issuefeedbacksystem.dto.*;
import com.issue.issuefeedbacksystem.entity.User;
import com.issue.issuefeedbacksystem.service.UserService;
import com.issue.issuefeedbacksystem.utils.JwtUtil;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public PagedResult<?> getPendingUserList(Integer size, Integer offset) {
        Integer total = userDAO.countPendingUserSum();
        if (total == 0) return PagedResult.success("待处理用户列表为空", 0,null);
        List<PendingUserBO> list = userDAO.selectPendingUserList(size, offset);
        return PagedResult.success("已成功获取待处理用户列表", total, list);
    }

    @Override
    public MsgResult setUserRole(UserBatchUpdateRoleDTO userBatchUpdateRoleDTO) {
        int row = userDAO.batchUpdateUserRole(userBatchUpdateRoleDTO);
        int invalid = userBatchUpdateRoleDTO.getUserIdList().size() - row;
        return row > 0 ? MsgResult.success(invalid == 0 ? "角色分配成功" : "分配成功, 无效分配" + invalid +"人")
                : MsgResult.fail("分配失败, 无效分配" + invalid +"人");
    }

    @Override
    public PagedResult<?> getUserList(Integer size, Integer offset) {
        Integer total = userDAO.countUserSum();
        if (total == 0) return PagedResult.fail("用户列表为空");
        List<UserBO> userList = userDAO.selectUserList(size, offset);
        return !userList.isEmpty()
                ? PagedResult.success("成功获取用户列表", total, userList)
                : PagedResult.success("当前记录后结果为空", 0 ,null);
    }

    @Override
    public MsgResult updateUser(UserUpdateDTO userUpdateDTO) {
        int row = userDAO.updateUser(userUpdateDTO);
        return row > 0 ? MsgResult.success("修改成功") : MsgResult.fail("修改失败");
    }

    @Override
    public MsgResult batchUpdateUserDept(UserBatchUpdateDeptDTO userBatchUpdateDeptDTO) {
        int row = userDAO.batchUpdateUserDept(userBatchUpdateDeptDTO);
        int invalid = userBatchUpdateDeptDTO.getUserIdList().size() - row;
        return row > 0 ? MsgResult.success(invalid == 0 ? "分配成功" : "分配成功, 无效分配" + invalid +"人")
                : MsgResult.fail("分配失败, 无效分配" + invalid +"人");
    }

    @Override
    public MsgResult deleteUser(List<Integer> userIdList) {
        int row = userDAO.deleteUserByUidList(userIdList);
        int invalid = userIdList.size() - row;
        return row > 0 ? MsgResult.success(invalid == 0 ? "删除成功" : "删除成功, 无效删除" + invalid +"人")
                : MsgResult.fail("删除失败, 无效删除" + invalid +"人");
    }

    @Override
    public PagedResult<?> getUserListByRole(Integer size, Integer offset, Integer roleId) {
        Integer total = userDAO.countUserSumByRole(roleId);
        if (total == 0) return PagedResult.fail("用户列表为空");
        List<UserBO> userList = userDAO.selectUserListByRole(size, offset, roleId);
        return !userList.isEmpty()
                ? PagedResult.success("成功获取用户列表", total, userList)
                : PagedResult.success("当前记录后结果为空", 0 ,null);
    }

    @Override
    public PagedResult<?> getUserListByDept(Integer size, Integer offset, Integer deptId) {
        Integer total = userDAO.countUserSumByDept(deptId);
        if (total == 0) return PagedResult.fail("用户列表为空");
        List<UserBO> userList = userDAO.selectUserListByDept(size, offset, deptId);
        return !userList.isEmpty()
                ? PagedResult.success("成功获取用户列表", total, userList)
                : PagedResult.success("当前记录后结果为空", 0 ,null);
    }

    @Override
    public PagedResult<?> getUserListByPhone(Integer size, Integer offset, String phone) {
        Integer total = userDAO.countUserSumByPhone(phone);
        if (total == 0) return PagedResult.fail("用户列表为空");
        List<UserBO> userList = userDAO.selectUserListByPhone(size, offset, phone);
        return !userList.isEmpty()
                ? PagedResult.success("成功获取用户列表", total, userList)
                : PagedResult.success("当前记录后结果为空", 0 ,null);
    }
}
