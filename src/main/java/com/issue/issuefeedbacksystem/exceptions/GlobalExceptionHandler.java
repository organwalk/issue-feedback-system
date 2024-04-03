package com.issue.issuefeedbacksystem.exceptions;

import com.issue.issuefeedbacksystem.vo.MsgResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

/**
 * 全局异常拦截
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 捕获 MethodArgumentNotValidException 异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public MsgResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(String.valueOf(e));
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError error : allErrors) {
            errorMsg.append(error.getDefaultMessage()).append(";");
        }
        return MsgResult.fail(errorMsg.toString());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public MsgResult runTimeError(RuntimeException re){
        log.error(String.valueOf(re.getClass()));
        return MsgResult.fail("内部服务错误，请稍后重试");
    }

    @ExceptionHandler(value = {Exception.class, IllegalArgumentException.class})
    public MsgResult handleInternalServerError(Exception e, IllegalArgumentException ae) {
        log.error(String.valueOf(e));
        log.error(String.valueOf(ae));
        return MsgResult.fail("内部服务错误，请稍后重试");
    }


}
