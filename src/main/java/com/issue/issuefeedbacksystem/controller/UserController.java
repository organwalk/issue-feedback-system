package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.UserBatchUpdateDeptDTO;
import com.issue.issuefeedbacksystem.dto.UserBatchUpdateRoleDTO;
import com.issue.issuefeedbacksystem.dto.UserUpdateDTO;
import com.issue.issuefeedbacksystem.service.UserService;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping("/pending-users")
    public PagedResult<?> getPendingUserList(@Valid
                                             @Min(value = 1, message = "size不能小于1")
                                             @RequestParam(value = "size") Integer size,
                                             @Min(value = 0, message = "offset不能小于0")
                                             @RequestParam(value = "offset") Integer offset) {
        return userService.getPendingUserList(size, offset);
    }

    @PutMapping("/users/role")
    public MsgResult setUserRole(@RequestBody UserBatchUpdateRoleDTO userBatchUpdateRoleDTO) {
        return userService.setUserRole(userBatchUpdateRoleDTO);
    }

    @GetMapping("/users")
    public PagedResult<?> getUserList(@Valid
                                      @Min(value = 1, message = "size不能小于1")
                                      @RequestParam(value = "size") Integer size,
                                      @Min(value = 0, message = "offset不能小于0")
                                      @RequestParam(value = "offset") Integer offset) {
        return userService.getUserList(size, offset);
    }

    @PutMapping("/user")
    public MsgResult updateUser(@Validated @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userUpdateDTO);
    }

    @PutMapping("/users/dept")
    public MsgResult batchUpdateUserDept(@Validated @RequestBody UserBatchUpdateDeptDTO userBatchUpdateDeptDTO) {
        return userService.batchUpdateUserDept(userBatchUpdateDeptDTO);
    }

    @DeleteMapping("/users")
    public MsgResult deleteUsers(@RequestParam List<Integer> uidList) {
        return userService.deleteUser(uidList);
    }

    @GetMapping("/users/role")
    public PagedResult<?> getUserListByRole(@Valid
                                            @Min(value = 1, message = "size不能小于1")
                                            @RequestParam(value = "size") Integer size,
                                            @Min(value = 0, message = "offset不能小于0")
                                            @RequestParam(value = "offset") Integer offset,
                                            @Min(value = 1, message = "roleId不能小于1")
                                            @RequestParam(value = "roleId") Integer roleId) {
        return userService.getUserListByRole(size, offset, roleId);
    }

    @GetMapping("/users/dept")
    public PagedResult<?> getUserListByDept(@Valid
                                            @Min(value = 1, message = "size不能小于1")
                                            @RequestParam(value = "size") Integer size,
                                            @Min(value = 0, message = "offset不能小于0")
                                            @RequestParam(value = "offset") Integer offset,
                                            @Min(value = 1, message = "deptId不能小于1")
                                            @RequestParam(value = "deptId") Integer deptId) {
        return userService.getUserListByDept(size, offset, deptId);
    }

    @GetMapping("/users/phone")
    public PagedResult<?> getUserListByPhone(@Valid
                                            @Min(value = 1, message = "size不能小于1")
                                            @RequestParam(value = "size") Integer size,
                                            @Min(value = 0, message = "offset不能小于0")
                                            @RequestParam(value = "offset") Integer offset,
                                            @NotBlank(message = "手机号码不能为空")
                                            @RequestParam(value = "phone") String phone) {
        return userService.getUserListByPhone(size, offset, phone);
    }

}
