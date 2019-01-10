package com.model.converter;

import com.model.dto.SmsFormDTO;
import com.model.entity.Sms;
import com.model.vo.SendSmsResponseVO;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/30 上午10:23
 */
public class SmsAssembly {

    public static Sms toDomain(SendSmsResponseVO sendSmsResponseVO, SmsFormDTO smsFormDTO, String captcha) {
        if (sendSmsResponseVO == null || smsFormDTO == null) {
            return null;
        }
        Sms sms = new Sms();
        sms.setRequestId(sendSmsResponseVO.getRequestId());
        sms.setBizId(sendSmsResponseVO.getBizId());
        sms.setCode(sendSmsResponseVO.getCode());
        sms.setMessage(sendSmsResponseVO.getMessage());
        sms.setPhone(smsFormDTO.getPhone());
        sms.setCaptcha(captcha);
        sms.setType(smsFormDTO.getType());
        return sms;
    }

}
