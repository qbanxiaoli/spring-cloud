package com.qbanxiaoli.sms.enums;

import com.qbanxiaoli.common.enums.ResponseEnum;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:30
 */
public enum SmsResponseEnum implements ResponseEnum {

    VARIABLE(true, 300, "VARIABLE"),
    SUCCESS(true, 200, "SUCCESS"),
    FAILURE(false, 400, "FAILURE"),
    PHONE_NOT_NULL(false, 501, "PHONE_NOT_NULL");

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
