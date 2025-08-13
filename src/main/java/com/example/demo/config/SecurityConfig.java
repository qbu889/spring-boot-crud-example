package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 注入密码加密器（之前已配置）
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置安全过滤链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // 关闭CSRF（开发环境简化配置）
//                .csrf().disable()
//                // 配置请求授权规则
//                .authorizeRequests()
//                // 允许匿名访问的接口（例如登录、注册）
//                .antMatchers("/api/users").permitAll() // 允许匿名创建用户
//                .antMatchers("/api/users/**").authenticated() // 其他用户接口需要认证
//                .anyRequest().authenticated() // 其他所有请求需要认证
//                .and()
//                // 开启表单登录（Spring Security默认提供的登录页面）
//                .formLogin()
//                .and()
//                // 开启基本认证（支持Postman等工具用账号密码登录）
//                .httpBasic();
        http
                // 关闭CSRF保护
                .csrf().disable()
                // 配置所有请求允许匿名访问（关闭认证）
                .authorizeRequests()
                .anyRequest().permitAll() // 关键：所有接口放行，无需认证
                .and()
                // 禁用默认登录页面和HTTP Basic认证
                .formLogin().disable()
                .httpBasic().disable();
        return http.build();
    }
}