package com.qbanxiaoli.sms.controller;

import com.qbanxiaoli.common.util.JsonUtil;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 3:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SmsManageControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void sendSms() throws Exception {
        SmsFormDTO smsFormDTO = new SmsFormDTO();
        smsFormDTO.setPhone("15957180964");
        smsFormDTO.setType(0);
        smsFormDTO.setProjectName("短信服务");
        smsFormDTO.setUserId(1L);
        log.info(JsonUtil.toJsonString(smsFormDTO));
        this.mockMvc.perform(post("/sms/send?lang=en_US")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJsonBytes(smsFormDTO)))
                .andExpect(status().isOk()).andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
    }

}
