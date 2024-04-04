package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.PendingUserRoleDTO;
import com.issue.issuefeedbacksystem.service.UserService;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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


    @PutMapping("/pending-users")
    public MsgResult setUserRole(@Validated @RequestBody List<PendingUserRoleDTO> pendingUserRoleDTOList){
        return userService.setUserRole(pendingUserRoleDTOList);
    }
}
