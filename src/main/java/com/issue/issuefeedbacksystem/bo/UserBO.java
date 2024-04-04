package com.issue.issuefeedbacksystem.bo;

import com.issue.issuefeedbacksystem.entity.Dept;
import com.issue.issuefeedbacksystem.entity.Role;
import lombok.Data;

@Data
public class UserBO {
    private Integer userId;
    private Integer username;
    private Role role;
    private Dept dept;
    private String phone;
}
