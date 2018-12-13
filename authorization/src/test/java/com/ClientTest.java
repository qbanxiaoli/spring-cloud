package com;

import com.entity.Authority;
import com.entity.User;
import com.repository.AuthorityRepository;
import com.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * @author qbanxiaoli
 * @description
 * @create 2018/5/9 19:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void Test() {
        User user = userRepository.findByUsername("qbanxiaoli");
        user.setPassword(passwordEncoder().encode("123456"));
        List<Authority> authorityList = authorityRepository.findAll();
        authorityList.remove(authorityList.get(0));
        user.setAuthorityList(authorityList);
        userRepository.save(user);
    }

    @Test
    public void saveAuthority() {
        Authority authority = new Authority();
        authority.setAuthority("TCM_EMPLOYEE");
        authorityRepository.save(authority);
    }

}
