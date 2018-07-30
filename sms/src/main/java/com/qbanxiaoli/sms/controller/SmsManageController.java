package com.qbanxiaoli.sms.controller;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.StringUtil;
import com.qbanxiaoli.sms.enums.SmsResponseEnum;
import com.qbanxiaoli.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:16
 */
@Slf4j
@RestController
public class SmsManageController {

    private final SmsService smsService;

    @Autowired
    public SmsManageController(SmsService smsService) {
        this.smsService = smsService;
    }

    /**
     * @param phone 手机号码
     * @return 请求响应
     * @author qbanxiaoli
     * @description 发送短信验证码
     */
    @GetMapping("/sendsms/{phone}")
    public ResponseVO sendMessage(@PathVariable("phone") String phone) {
        log.info("发送短信验证码请求");
        log.info("进行参数校验");
        // 参数处理判断
        if (!StringUtil.isNotBlank(phone)) {
            log.info("手机号码不能为空");
            return new ResponseVO(SmsResponseEnum.PHONE_NOT_NULL);
        }
        log.info("参数校验正常");
        return smsService.sendMessage(phone);
    }

}
