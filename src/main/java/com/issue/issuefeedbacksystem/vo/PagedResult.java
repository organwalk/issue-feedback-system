package com.issue.issuefeedbacksystem.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagedResult <T>{
    private int code;
    private String msg;
    private int total;
    private List<T> data;

    public static <T>PagedResult<T> success(String msg, int total, List<T> data){
        return PagedResult.<T>builder()
                .code(Code.SUCCESS)
                .msg(msg)
                .total(total)
                .data(data)
                .build();
    }

    public static <T>PagedResult<T> fail(String msg, int total, List<T> data){
        return PagedResult.<T>builder()
                .code(Code.FAIL)
                .msg(msg)
                .total(total)
                .data(data)
                .build();
    }
}
