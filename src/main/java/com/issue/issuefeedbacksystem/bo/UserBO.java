package com.issue.issuefeedbacksystem.bo;

import lombok.Data;

@Data
public class UserBO {
    private Integer userId;
    private String username;
    private RoleBO role;
    private DeptBO dept;
    private String phone;
}
