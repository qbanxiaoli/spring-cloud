package com.controller;

import com.model.vo.ResponseVO;
import com.model.dto.SmsFormDTO;
import com.model.vo.SendSmsResponseVO;
import com.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:16
 */
@Slf4j
@RestController
@Api(tags = "短信写模块")
@RequestMapping("/sms")
public class SmsManageController {

    private final SmsService smsService;

    @Autowired
    public SmsManageController(SmsService smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @ApiOperation(value = "发送短信验证码")
    @PostMapping("/send")
    public ResponseVO<SendSmsResponseVO> sendSms(@ApiParam(name = "smsFormDTO", value = "短信请求数据传输模型", required = true)
                                                 @Validated @RequestBody SmsFormDTO smsFormDTO) {
        log.info("发送短信验证码");
        return smsService.sendSms(smsFormDTO);
    }

}
