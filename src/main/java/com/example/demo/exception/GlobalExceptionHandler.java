package com.example.demo.exception;

import com.example.demo.common.ApiResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理参数校验异常（如@Valid校验失败）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidationException(MethodArgumentNotValidException e) {
        // 提取第一个错误信息
        FieldError firstError = e.getBindingResult().getFieldErrors().get(0);
        String errorMsg = firstError.getField() + ": " + firstError.getDefaultMessage();
        return ApiResponse.fail(400, errorMsg); // 400-参数错误
    }

    // 处理业务异常（如用户已存在）
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Void> handleBusinessException(RuntimeException e) {
        return ApiResponse.fail(500, e.getMessage()); // 500-业务错误
    }

    // 处理其他未知异常
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleUnknownException(Exception e) {
        return ApiResponse.fail(500, "服务器内部错误：" + e.getMessage());
    }
}