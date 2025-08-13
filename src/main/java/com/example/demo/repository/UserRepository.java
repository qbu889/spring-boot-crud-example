package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // 根据用户名查询
    Optional<User> findByUsername(String username);
    
    // 根据邮箱查询
    Optional<User> findByEmail(String email);
    
    // 根据年龄范围查询
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);
    
    // 分页查询
    Page<User> findByUsernameContaining(String keyword, Pageable pageable);
}
