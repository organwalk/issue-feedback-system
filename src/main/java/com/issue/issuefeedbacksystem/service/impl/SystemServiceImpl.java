package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.dao.SystemDAO;
import com.issue.issuefeedbacksystem.entity.Role;
import com.issue.issuefeedbacksystem.service.SystemService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
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
        List<Role> roles = systemDAO.selectRoleList();
        return !roles.isEmpty()
                ? CommonResult.success("成功获取角色列表", roles)
                : CommonResult.fail("角色列表为空");
    }
}
