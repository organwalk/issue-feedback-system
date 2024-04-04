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
        // 从列表中筛选出有效用户列表
        List<Integer> realUidList = userDAO.selectEffectUidByUidList(userBatchUpdateRoleDTO.getUserIdList());
        if (realUidList.isEmpty()) return MsgResult.fail("当前用户不存在或已删除");
        // 获取无效人数,并将DTO的用户列表替换为有效用户列表
        int invalid = userBatchUpdateRoleDTO.getUserIdList().size() - realUidList.size();
        userBatchUpdateRoleDTO.setUserIdList(realUidList);

        // 批量修改
        int row = userDAO.batchUpdateUserRole(userBatchUpdateRoleDTO);
        return row > 0 ? MsgResult.success(invalid == 0 ? "角色分配成功" : "分配成功, 无效人数为:" + invalid +"人")
                : MsgResult.fail("分配失败");
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

    @Override
    public MsgResult updateUser(UserUpdateDTO userUpdateDTO) {
        int row = userDAO.updateUser(userUpdateDTO);
        return row > 0 ? MsgResult.success("修改成功") : MsgResult.fail("修改失败");
    }

    @Override
    public MsgResult batchUpdateUserDept(UserBatchUpdateDeptDTO userBatchUpdateDeptDTO) {
        // 从列表中筛选出有效用户列表
        List<Integer> realUidList = userDAO.selectTeaAndDeptLeaderListByUidList(userBatchUpdateDeptDTO.getUserIdList());
        if (realUidList.isEmpty()) return MsgResult.fail("当前用户角色非老师或部门领导");
        // 获取无效人数,并将DTO的用户列表替换为有效用户列表
        int invalid = userBatchUpdateDeptDTO.getUserIdList().size() - realUidList.size();
        userBatchUpdateDeptDTO.setUserIdList(realUidList);

        // 批量修改
        int row = userDAO.batchUpdateUserDept(userBatchUpdateDeptDTO);
        return row > 0 ? MsgResult.success(invalid == 0 ? "修改成功" : "修改成功, 无效人数为:" + invalid +"人")
                : MsgResult.fail("修改失败");
    }
}
