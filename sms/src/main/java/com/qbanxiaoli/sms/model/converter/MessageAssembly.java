package com.qbanxiaoli.sms.model.converter;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
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
        if (sendSmsResponse.getRequestId() != null) {
            message.setRequestId(sendSmsResponse.getRequestId());
        }
        if (sendSmsResponse.getBizId() != null) {
            message.setBizId(sendSmsResponse.getBizId());
        }
        if (sendSmsResponse.getCode() != null) {
            message.setCode(sendSmsResponse.getCode());
        }
        if (sendSmsResponse.getMessage() != null) {
            message.setMessage(sendSmsResponse.getMessage());
        }
        if (phone != null) {
            message.setPhone(phone);
        }
        if (captcha != null) {
            message.setCaptcha(captcha);
        }
        message.setType(MessageConstant.SIGN_UP);
        return message;
    }

}
