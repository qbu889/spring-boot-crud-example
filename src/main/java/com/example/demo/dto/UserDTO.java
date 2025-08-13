package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer age;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
