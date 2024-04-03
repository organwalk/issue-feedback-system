package com.issue.issuefeedbacksystem.bo;

import lombok.Data;

@Data
public class PendingUserBO {
    private Integer userId;
    private String username;
    private String roleName;
    private String phone;
}
