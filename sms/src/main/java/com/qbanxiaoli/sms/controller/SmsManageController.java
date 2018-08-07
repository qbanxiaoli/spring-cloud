package com.qbanxiaoli.sms.controller;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.StringUtil;
import com.qbanxiaoli.sms.enums.SmsResponseEnum;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
                                  @Valid @RequestBody SmsFormDTO smsFormDTO,
                                  BindingResult bindingResult) {
        log.info("发送短信验证码请求");
        log.info("进行参数校验");
        if (bindingResult.hasErrors()) {
            for (ObjectError errors : bindingResult.getAllErrors()) {
                log.info("参数错误：" + errors.getDefaultMessage());
            }
            return new ResponseVO(SmsResponseEnum.FAILURE_VARIABLE, new Object[]{bindingResult.getAllErrors().get(0).getDefaultMessage()});
        }
        log.info("参数校验正常");
        return smsService.sendMessage(smsFormDTO);
    }

}
