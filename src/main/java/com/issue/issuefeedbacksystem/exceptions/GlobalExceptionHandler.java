package com.issue.issuefeedbacksystem.exceptions;

import com.issue.issuefeedbacksystem.vo.MsgResult;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常拦截
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 捕获 MethodArgumentNotValidException 异常
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
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
    public MsgResult handleRuntimeException(RuntimeException re){
        log.error("RuntimeException occurred: {}", re.getMessage(), re);
        return MsgResult.fail("内部服务错误，请稍后重试");
    }

    @ExceptionHandler(value = {Exception.class, IllegalArgumentException.class})
    public MsgResult handleOtherExceptions(Exception e) {
        log.error("Exception occurred: {}", e.getMessage(), e);
        return MsgResult.fail("内部服务错误，请稍后重试");
    }

    @ExceptionHandler(value = BindException.class)
    public MsgResult exceptionHandler(BindException e){
        String messages = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return MsgResult.fail(messages);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public MsgResult methodArgumentNotValid(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("；"));
        return MsgResult.fail(message);
    }



}
