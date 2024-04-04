package com.issue.issuefeedbacksystem.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String username;
    private String passwordHash;
    private Integer roleId;
    private Integer deptId;
    private String phone;
}
