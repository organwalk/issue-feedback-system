package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.service.RoleService;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
@Validated
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/roles")
    public CommonResult<?> getRoleList() {
        return roleService.getRoleList();
    }

    @PostMapping("/role")
    public MsgResult addRole(@Valid @NotBlank(message = "roleName不能为空")
                             @RequestParam String roleName) {
        return roleService.addRole(roleName);
    }

    @PutMapping("/role")
    public MsgResult updateRole(@Valid @NotBlank(message = "roleName不能为空")
                                @RequestParam String roleName,
                                @Min(value = 1, message = "roleId不能小于1")
                                @RequestParam Integer roleId){
        return roleService.updateRole(roleName, roleId);
    }

    @DeleteMapping("/role")
    public MsgResult deleteRole(@RequestParam List<Integer> roleIdList){
        return roleService.deleteRole(roleIdList);
    }
}
