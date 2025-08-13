package com.example.demo.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;

    // 用户名非空校验
    @NotBlank(message = "用户名不能为空")
    private String username;

    // 密码非空校验
    @NotBlank(message = "密码不能为空")
    private String password;

    // 邮箱格式校验（必须包含@和域名）
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",
            message = "邮箱格式不正确（例如：xxx@xxx.com）")
    private String email;

    // 手机号格式校验（11位数字，以1开头）
    @Pattern(regexp = "^1[3-9]\\d{9}$",
            message = "手机号格式不正确（需为11位有效数字且符合手机开头数字）")
    private String phone;

    // 年龄非负校验
    @PositiveOrZero(message = "年龄不能为负数")
    private Integer age;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}