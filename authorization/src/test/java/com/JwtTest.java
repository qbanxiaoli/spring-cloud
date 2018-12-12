package com;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/12 20:47
 */
@Slf4j
public class JwtTest {

    @Test
    public void test() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDQ3MTEzNjAsInV1aWQiOiJhZDJlMzMzMTk3NGM0Y2RhYmY1MTBmNDlmNDliNTdiMSIsInVzZXJfbmFtZSI6ImFkbWluIiwianRpIjoiZTJkM2MxOWQtMGVmMS00Y2Q0LWI2MDctNDJmNzBhYjg1MGYwIiwiY2xpZW50X2lkIjoiaHEiLCJzY29wZSI6WyJ4eCJdfQ.cz41a5RphfVavmU61045lPXQm4WJZGTNGFiL_9XP5bU";
        Jwt jwt = JwtHelper.decode(token);
        log.info(jwt.toString());
    }

}
