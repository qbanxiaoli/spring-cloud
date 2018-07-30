package com.qbanxiaoli.sms.model.converter;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.qbanxiaoli.common.util.StringUtil;
import com.qbanxiaoli.sms.constant.MessageConstant;
import com.qbanxiaoli.sms.model.entity.Message;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/30 上午10:23
 */
public class MessageAssembly {

    public static Message toDomain(SendSmsResponse sendSmsResponse, String phone, String captcha) {
        if (sendSmsResponse == null) {
            return null;
        }
        Message message = new Message();
        if (StringUtil.isNotBlank(sendSmsResponse.getRequestId())) {
            message.setRequestId(sendSmsResponse.getRequestId());
        }
        if (StringUtil.isNotBlank(sendSmsResponse.getBizId())) {
            message.setBizId(sendSmsResponse.getBizId());
        }
        if (StringUtil.isNotBlank(sendSmsResponse.getCode())) {
            message.setCode(sendSmsResponse.getCode());
        }
        if (StringUtil.isNotBlank(sendSmsResponse.getMessage())) {
            message.setMessage(sendSmsResponse.getMessage());
        }
        if (StringUtil.isNotBlank(phone)) {
            message.setPhone(phone);
        }
        if (StringUtil.isNotBlank(captcha)) {
            message.setCaptcha(captcha);
        }
        message.setType(MessageConstant.SIGN_UP);
        return message;
    }

}
