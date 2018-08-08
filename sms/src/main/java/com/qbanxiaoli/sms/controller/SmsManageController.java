package com.qbanxiaoli.sms.controller;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return 请求响应
     * @author qbanxiaoli
     * @description 获取短信验证码
     */
    @ApiOperation(value = "获取短信验证码", notes = "用户获取短信验证码")
    @PostMapping("/sendsms")
    public ResponseVO sendMessage(@ApiParam(name = "smsFormDTO", value = "短信请求数据传输类", required = true)
                                  @Valid @RequestBody SmsFormDTO smsFormDTO) {
        log.info("发送短信验证码请求");
        log.info("进行参数校验");
        log.info("参数校验正常");
        return smsService.sendMessage(smsFormDTO);
    }

}
