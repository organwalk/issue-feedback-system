package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.bo.RoleBO;
import com.issue.issuefeedbacksystem.dao.RoleDAO;
import com.issue.issuefeedbacksystem.service.RoleService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Override
    public CommonResult<?> getRoleList() {
        List<RoleBO> roles = roleDAO.selectRoleListNotDelete();
        return !roles.isEmpty()
                ? CommonResult.success("成功获取角色列表", roles)
                : CommonResult.fail("角色列表为空");
    }

    @Override
    public MsgResult addRole(String roleName) {
        Integer roleId = roleDAO.selectRoleIdByRoleName(roleName);
        if (roleId != null) return MsgResult.fail("已存在该角色，无需添加");
        int row = roleDAO.insertRoleName(roleName);
        return row > 0 ? MsgResult.success("添加角色成功") : MsgResult.fail("添加角色失败");
    }

    @Override
    public MsgResult updateRole(String roleName, Integer roleId) {
        Integer roleIdMark = roleDAO.selectRoleIdByRoleName(roleName);
        if (roleIdMark != null) return MsgResult.fail("修改失败，角色名称已存在");
        int row = roleDAO.updateRoleName(roleName, roleId);
        return row > 0 ? MsgResult.success("修改角色成功") : MsgResult.fail("修改角色失败");
    }

    @Override
    public MsgResult deleteRole(List<Integer> roleIdList) {
        int row = roleDAO.batchDeleteRole(roleIdList);
        int invalid = roleIdList.size() - row;
        return row > 0 ? MsgResult.success(invalid == 0 ? "删除角色成功" : "删除角色成功, 无效删除角色"+ invalid + "个")
                : MsgResult.fail("删除角色失败，无效删除角色" + invalid + "个");
    }
}
