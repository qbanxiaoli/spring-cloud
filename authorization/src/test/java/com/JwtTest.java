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
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDQ3MTExNDMsInV1aWQiOiJhZDJlMzMzMTk3NGM0Y2RhYmY1MTBmNDlmNDliNTdiMSIsInVzZXJfbmFtZSI6ImFkbWluIiwianRpIjoiNTVkZWQ0YjYtZThjNC00OWI3LTljNTQtNzdkZTk1OWZiODY4IiwiY2xpZW50X2lkIjoiaHEiLCJzY29wZSI6WyJ4eCJdfQ.71FTWTCxzJYiE_0_1QyGtxWaSfW7eS0WWo36Av8xPgE";
        Jwt jwt = JwtHelper.decode(token);
        log.info(jwt.toString());
    }

}
