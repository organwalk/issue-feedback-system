package com.issue.issuefeedbacksystem.entity;

import lombok.Data;

@Data
public class Role {
    private Integer roleId;
    private String roleName;
    private int is_delete;
}
