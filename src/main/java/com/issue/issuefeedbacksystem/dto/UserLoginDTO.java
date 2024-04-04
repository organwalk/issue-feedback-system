package com.issue.issuefeedbacksystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserLoginDTO {
    @Pattern(regexp = "^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$",message = "手机号码格式错误")
    private String phone;
    @NotBlank(message = "密码不能为空")
    private String password;
}
