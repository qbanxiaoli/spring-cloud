package com.qbanxiaoli.sms.enums;

import com.qbanxiaoli.common.enums.ResponseEnum;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:30
 */
public enum SmsResponseEnum implements ResponseEnum {

    VARIABLE("true", "VARIABLE", "300"),
    SUCCESS("true","SUCCESS","200"),
    FAILURE("false", "FAILURE", "400"),
    PHONE_NOT_NULL("false", "PHONE_NOT_NULL", "501");

    private String success;
    private String message;
    private String code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getSuccess() {
        return success;
    }

    @Override
    public String getCode() {
        return code;
    }

    SmsResponseEnum(String success, String message, String code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

}
