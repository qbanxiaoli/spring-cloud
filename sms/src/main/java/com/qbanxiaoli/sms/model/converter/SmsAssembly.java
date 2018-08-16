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
        if (StringUtil.isNotBlank(sendSmsResponseVO.getRequestId())) {
            sms.setRequestId(sendSmsResponseVO.getRequestId());
        }
        if (StringUtil.isNotBlank(sendSmsResponseVO.getBizId())) {
            sms.setBizId(sendSmsResponseVO.getBizId());
        }
        if (StringUtil.isNotBlank(sendSmsResponseVO.getCode())) {
            sms.setCode(sendSmsResponseVO.getCode());
        }
        if (StringUtil.isNotBlank(sendSmsResponseVO.getMessage())) {
            sms.setMessage(sendSmsResponseVO.getMessage());
        }
        if (StringUtil.isNotBlank(smsFormDTO.getPhone())) {
            sms.setPhone(smsFormDTO.getPhone());
        }
        if (StringUtil.isNotBlank(smsFormDTO.getProjectName())) {
            sms.setProjectName(smsFormDTO.getProjectName());
        }
        if (StringUtil.isNotBlank(captcha)) {
            sms.setCaptcha(captcha);
        }
        if (smsFormDTO.getUserId() != null) {
            sms.setUserId(smsFormDTO.getUserId());
        }
        if (smsFormDTO.getType() != null) {
            sms.setType(smsFormDTO.getType());
        }
        return sms;
    }

}
