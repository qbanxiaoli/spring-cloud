package com.bean;

import com.entity.Authority;
import com.entity.User;
import com.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/14 17:56
 */
@Slf4j
@Component
public class UserResource {

    private static UserRepository userRepository;

    @Autowired
    public void setUserResource(UserRepository userRepository) {
        UserResource.userRepository = userRepository;
    }

    public static User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        log.info("登录模式：密码模式");
        log.info("登录用户: " + user.getUsername());
        log.info("用户ID: " + user.getUuid());
        log.info("用户权限列表:");
        user.getAuthorityList().forEach(authority -> {
            log.info(authority.getAuthority());
        });
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Authority role : user.getAuthorityList()) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        user.setAuthorities(authorities);
        return user;
    }

    public static User findByPhone(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("手机号码不存在");
        }
        log.info("登录模式：短信验证模式");
        log.info("登录用户: " + user.getUsername());
        log.info("用户ID: " + user.getUuid());
        log.info("用户权限列表:");
        user.getAuthorityList().forEach(authority -> {
            log.info(authority.getAuthority());
        });
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Authority role : user.getAuthorityList()) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        user.setAuthorities(authorities);
        return user;
    }

    public static User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("邮箱号码不存在");
        }
        log.info("登录模式：邮箱验证模式");
        log.info("登录用户: " + user.getUsername());
        log.info("用户ID: " + user.getUuid());
        log.info("用户权限列表:");
        user.getAuthorityList().forEach(authority -> {
            log.info(authority.getAuthority());
        });
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Authority role : user.getAuthorityList()) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        user.setAuthorities(authorities);
        return user;
    }

}
