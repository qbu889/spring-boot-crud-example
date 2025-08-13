package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        // 检查用户名是否存在
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("用户名已存在: " + username);
        }
        // 新增：检查邮箱是否存在
        String email = userDTO.getEmail();
        if (email != null && userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("邮箱已存在: " + email);
        }
        User user= convertToEntity(userDTO);
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDTO(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return convertToDTO(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getUsersByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersByCondition(String keyword, Integer minAge, Integer maxAge) {
        List<User> users;
        
        if (keyword != null && minAge != null && maxAge != null) {
            users = userRepository.findByUsernameContaining(keyword, PageRequest.of(0, 100))
                    .getContent()
                    .stream()
                    .filter(u -> u.getAge() != null && u.getAge() >= minAge && u.getAge() <= maxAge)
                    .collect(Collectors.toList());
        } else if (keyword != null) {
            users = userRepository.findByUsernameContaining(keyword, PageRequest.of(0, 100)).getContent();
        } else if (minAge != null && maxAge != null) {
            users = userRepository.findByAgeBetween(minAge, maxAge);
        } else {
            users = userRepository.findAll();
        }
        
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setAge(userDTO.getAge());
        
        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }
    
    @Override
    @Transactional
    public UserDTO partialUpdateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPhone() != null) {
            existingUser.setPhone(userDTO.getPhone());
        }
        if (userDTO.getAge() != null) {
            existingUser.setAge(userDTO.getAge());
        }
        
        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }
    
    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    
    // 转换方法：DTO -> 实体
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAge(userDTO.getAge());
        return user;
    }
    
    // 转换方法：实体 -> DTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAge(user.getAge());
        userDTO.setCreatedTime(user.getCreatedTime());
        userDTO.setUpdatedTime(user.getUpdatedTime());
        return userDTO;
    }
}
