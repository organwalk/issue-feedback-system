package com.issue.issuefeedbacksystem.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MsgResult {
    private int code;
    private String msg;

    public static MsgResult success(String msg){
        return MsgResult.builder()
                .code(Code.SUCCESS)
                .msg(msg)
                .build();
    }

    public static MsgResult fail(String msg){
        return MsgResult.builder()
                .code(Code.FAIL)
                .msg(msg)
                .build();
    }

    public static MsgResult notAuth(){
        return MsgResult.builder()
                .code(Code.NOT_AUTH)
                .msg("授权凭证已过期或不存在")
                .build();
    }

    public static MsgResult forbidden(){
        return MsgResult.builder()
                .code(Code.FORBIDDEN)
                .msg("暂无权限访问")
                .build();
    }
}
