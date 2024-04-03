package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.bo.RoleBO;
import com.issue.issuefeedbacksystem.dao.SystemDAO;
import com.issue.issuefeedbacksystem.entity.Role;
import com.issue.issuefeedbacksystem.service.SystemService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SystemServiceImpl implements SystemService {
    private final SystemDAO systemDAO;

    @Override
    public CommonResult<?> getRoleList() {
        List<RoleBO> roles = systemDAO.selectRoleList();
        return !roles.isEmpty()
                ? CommonResult.success("成功获取角色列表", roles)
                : CommonResult.fail("角色列表为空");
    }

    @Override
    public MsgResult addRole(String roleName) {
        int row = systemDAO.insertRoleName(roleName);
        return row > 0 ? MsgResult.success("添加角色成功") : MsgResult.fail("添加角色失败");
    }

    @Override
    public MsgResult updateRole(String roleName, Integer roleId) {
        int row = systemDAO.updateRoleName(roleName, roleId);
        return row > 0 ? MsgResult.success("修改角色成功") : MsgResult.fail("修改角色失败");
    }

    @Override
    public MsgResult deleteRole(List<Integer> roleIdList) {
        int row = systemDAO.batchDeleteRole(roleIdList);
        return row > 0 ? MsgResult.success("删除角色成功") : MsgResult.fail("删除角色失败");
    }
}
