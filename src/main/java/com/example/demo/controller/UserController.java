package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    // 创建用户
    @PostMapping
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ApiResponse.success(createdUser);
    }
    
    // 根据ID查询用户
    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ApiResponse.success(userDTO);
    }
    
    // 根据用户名查询用户
    @GetMapping("/username/{username}")
    public ApiResponse<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        return ApiResponse.success(userDTO);
    }
    
    // 查询所有用户
    @GetMapping
    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ApiResponse.success(users);
    }
    
    // 分页查询用户
    @GetMapping("/page")
    public ApiResponse<Page<UserDTO>> getUsersByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserDTO> users = userService.getUsersByPage(page, size);
        return ApiResponse.success(users);
    }
    
    // 条件查询用户
    @GetMapping("/search")
    public ApiResponse<List<UserDTO>> getUsersByCondition(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        List<UserDTO> users = userService.getUsersByCondition(keyword, minAge, maxAge);
        return ApiResponse.success(users);
    }
    
    // 全量更新用户
    @PutMapping("/{id}")
    public ApiResponse<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ApiResponse.success(updatedUser);
    }
    
    // 部分更新用户
    @PatchMapping("/{id}")
    public ApiResponse<UserDTO> partialUpdateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.partialUpdateUser(id, userDTO);
        return ApiResponse.success(updatedUser);
    }
    
    // 删除用户
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.success(); // 无数据的成功响应
    }
}
