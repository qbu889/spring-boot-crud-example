package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Email(message = "Invalid email format")
    private String email;
    private String phone;
    private Integer age;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
