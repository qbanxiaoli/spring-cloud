package com.qbanxiaoli.tool.model.converter;

import com.qbanxiaoli.tool.model.dto.SmsFormDTO;
import com.qbanxiaoli.tool.model.entity.Sms;
import com.qbanxiaoli.tool.model.vo.SendSmsResponseVO;

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
