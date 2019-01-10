package com.redis;

import com.model.dto.SmsFormDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author qbanxiaoli
 * @description
 * @create 2019-01-09 11:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void Test() {
        stringRedisTemplate.opsForValue().set("name", "老王", 60, TimeUnit.SECONDS);
        String name = stringRedisTemplate.opsForValue().get("name");
        log.info(name);

        SmsFormDTO smsFormDTO = new SmsFormDTO();
        smsFormDTO.setPhone("15957180964");
        smsFormDTO.setType(1);
        redisTemplate.opsForValue().set("smsFormDTO", smsFormDTO, 60, TimeUnit.SECONDS);

        SmsFormDTO result = (SmsFormDTO) redisTemplate.opsForValue().get("smsFormDTO");
        if (result != null) {
            log.info(result.getPhone());
            log.info(result.getType().toString());
        }

    }

}
