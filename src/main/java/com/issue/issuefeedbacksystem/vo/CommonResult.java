package com.issue.issuefeedbacksystem.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResult <T>{
    private int code;
    private String msg;
    private T data;

    public static <T>CommonResult<T> success(String msg, T data){
        return CommonResult.<T>builder()
                .code(Code.SUCCESS)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T>CommonResult<T> fail(String msg){
        return CommonResult.<T>builder()
                .code(Code.FAIL)
                .msg(msg)
                .build();
    }

}
