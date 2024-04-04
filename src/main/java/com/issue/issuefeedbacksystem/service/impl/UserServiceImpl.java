package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.bo.PendingUserBO;
import com.issue.issuefeedbacksystem.bo.UserBO;
import com.issue.issuefeedbacksystem.dao.UserDAO;
import com.issue.issuefeedbacksystem.dto.PendingUserRoleDTO;
import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
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
        if (total == 0) return PagedResult.fail("待处理用户列表为空");
        List<PendingUserBO> list = userDAO.selectPendingUserList(size, offset);
        return PagedResult.success("已成功获取待处理用户列表", total, list);
    }

    @Override
    public MsgResult setUserRole(List<PendingUserRoleDTO> pendingUserRoleDTOList) {
        for (PendingUserRoleDTO p : pendingUserRoleDTOList){
            userDAO.updateUserRoles(p.getUserId(), p.getRoleId());
        }
        return MsgResult.success("角色分配成功");
    }

    @Override
    public PagedResult<?> getUserList(Integer size, Integer offset) {
        Integer total = userDAO.countUserSum();
        if (total == 0) return PagedResult.fail("用户列表为空");
        List<UserBO> userList = userDAO.selectUserList(size, offset);
        return !userList.isEmpty()
                ? PagedResult.success("成功获取用户列表", total, userList)
                : PagedResult.fail("当前记录后结果为空");
    }
}
