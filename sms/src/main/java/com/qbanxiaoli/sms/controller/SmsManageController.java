package com.qbanxiaoli.sms.controller;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.vo.SendSmsResponseVO;
import com.qbanxiaoli.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:16
 */
@Slf4j
@RestController
@Api(tags = "发送短信")
public class SmsManageController {

    private final SmsService smsService;

    @Autowired
    public SmsManageController(SmsService smsService) {
        this.smsService = smsService;
    }

    @ApiOperation(value = "获取短信验证码", notes = "用户获取短信验证码")
    @PostMapping("/sms/send")
    public ResponseVO<SendSmsResponseVO> sendSms(@ApiParam(name = "smsFormDTO", value = "短信请求数据传输类", required = true)
                                                 @Validated @RequestBody SmsFormDTO smsFormDTO) {
        log.info("开始调用发送短信验证码服务");
        return smsService.sendSms(smsFormDTO);
    }

}
