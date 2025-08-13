package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    
    // 创建用户
    UserDTO createUser(UserDTO userDTO);
    
    // 根据ID查询用户
    UserDTO getUserById(Long id);
    
    // 根据用户名查询用户
    UserDTO getUserByUsername(String username);
    
    // 查询所有用户
    List<UserDTO> getAllUsers();
    
    // 分页查询用户
    Page<UserDTO> getUsersByPage(int page, int size);
    
    // 条件查询用户
    List<UserDTO> getUsersByCondition(String keyword, Integer minAge, Integer maxAge);
    
    // 更新用户
    UserDTO updateUser(Long id, UserDTO userDTO);
    
    // 部分更新用户信息
    UserDTO partialUpdateUser(Long id, UserDTO userDTO);
    
    // 删除用户
    void deleteUser(Long id);
}
