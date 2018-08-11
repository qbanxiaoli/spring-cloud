package com.qbanxiaoli.sms.model.converter;

import com.qbanxiaoli.common.util.StringUtil;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.Message;
import com.qbanxiaoli.sms.model.vo.SendSmsResponseVO;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/30 上午10:23
 */
public class MessageAssembly {

    public static Message toDomain(SendSmsResponseVO sendSmsResponseVO, SmsFormDTO smsFormDTO, String captcha) {
        if (sendSmsResponseVO == null || smsFormDTO == null) {
            return null;
        }
        Message message = new Message();
        if (StringUtil.isNotBlank(sendSmsResponseVO.getRequestId())) {
            message.setRequestId(sendSmsResponseVO.getRequestId());
        }
        if (StringUtil.isNotBlank(sendSmsResponseVO.getBizId())) {
            message.setBizId(sendSmsResponseVO.getBizId());
        }
        if (StringUtil.isNotBlank(sendSmsResponseVO.getCode())) {
            message.setCode(sendSmsResponseVO.getCode());
        }
        if (StringUtil.isNotBlank(sendSmsResponseVO.getMessage())) {
            message.setMessage(sendSmsResponseVO.getMessage());
        }
        if (StringUtil.isNotBlank(smsFormDTO.getPhone())) {
            message.setPhone(smsFormDTO.getPhone());
        }
        if (StringUtil.isNotBlank(captcha)) {
            message.setCaptcha(captcha);
        }
        if (smsFormDTO.getType() != null) {
            message.setType(smsFormDTO.getType());
        }
        message.setGmtCreate(System.currentTimeMillis());
        message.setGmtModified(System.currentTimeMillis());
        return message;
    }

}
