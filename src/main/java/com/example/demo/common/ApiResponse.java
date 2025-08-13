package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一API响应格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    // 状态码：0-成功，非0-失败
    private int code;
    // 消息提示
    private String msg;
    // 响应数据
    private T data;

    // 成功响应（带数据）
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(0, "success", data);
    }

    // 成功响应（无数据）
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(0, "success", null);
    }

    // 失败响应
    public static <T> ApiResponse<T> fail(int code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }
}