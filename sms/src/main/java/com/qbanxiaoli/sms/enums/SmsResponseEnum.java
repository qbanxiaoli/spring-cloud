package com.qbanxiaoli.sms.enums;

import com.qbanxiaoli.common.enums.ResponseEnum;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:30
 */
public enum SmsResponseEnum implements ResponseEnum {

    VARIABLE(true, 300, "VARIABLE"),//传入变量
    SUCCESS(true, 200, "SUCCESS"),//成功
    MSG_SEND_SUCCESS(true, 201, "MSG_SEND_SUCCESS"),//短信验证码发送成功
    FAILURE(false, 400, "FAILURE"),//失败
    MSG_SEND_FAILURE(false, 401, "MSG_SEND_FAILURE"),//短信验证码发送失败
    MSG_SAVE_FAILURE(false, 402, "MSG_SAVE_FAILURE"),//短信保存失败
    PHONE_NOT_NULL(false, 501, "PHONE_NOT_NULL");//手机号码不能为空

    private Boolean result;
    private Integer code;
    private String message;

    @Override
    public Boolean getResult() {
        return result;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    SmsResponseEnum(Boolean result, Integer code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

}
