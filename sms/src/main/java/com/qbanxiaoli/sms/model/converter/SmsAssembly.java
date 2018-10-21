package com.qbanxiaoli.sms.model.converter;

import com.qbanxiaoli.common.util.StringUtil;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.Sms;
import com.qbanxiaoli.sms.model.vo.SendSmsResponseVO;

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
        sms.setProjectName(smsFormDTO.getProjectName());
        sms.setCaptcha(captcha);
        sms.setUserId(smsFormDTO.getUserId());
        sms.setType(smsFormDTO.getType());
//        sms.setGmtCreated(System.currentTimeMillis());
//        sms.setGmtModified(System.currentTimeMillis());
        return sms;
    }

}
