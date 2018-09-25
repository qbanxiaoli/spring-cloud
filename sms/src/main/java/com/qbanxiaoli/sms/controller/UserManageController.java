package com.qbanxiaoli.sms.controller;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.vo.SendSmsResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 19:36
 */
@Slf4j
@RestController
@Api(tags = "用户模块")
public class UserManageController {

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return 请求响应
     * @author qbanxiaoli
     * @description 获取短信验证码
     */
//    @ApiOperation(value = "获取短信验证码", notes = "用户获取短信验证码")
//    @PostMapping("/register")
//    public ResponseVO<SendSmsResponseVO> sendSms(@ApiParam(name = "smsFormDTO", value = "短信请求数据传输类", required = true)
//                                                 @Valid @RequestBody SmsFormDTO smsFormDTO) {
//        log.info("参数校验正常");
//        log.info("开始发送短信验证码");
//        return smsService.sendSms(smsFormDTO);
//    }

}
