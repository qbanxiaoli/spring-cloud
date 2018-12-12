package com;

import com.entity.User;
import com.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void Test() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder().encode("123456"));
        userRepository.save(user);
    }

}
