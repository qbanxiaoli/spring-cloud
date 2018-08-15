package com.qbanxiaoli.sms.enums;

import com.qbanxiaoli.common.enums.ResponseEnum;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:30
 */
public enum SmsResponseEnum implements ResponseEnum {

    MSG_SEND_SUCCESS(true, 201, "MSG_SEND_SUCCESS"),//短信验证码发送成功
    MSG_SEND_FAILURE(false, 402, "MSG_SEND_FAILURE"),//短信验证码发送失败
    MSG_SAVE_FAILURE(false, 403, "MSG_SAVE_FAILURE"),//短信保存失败

    PHONE_NOT_NULL(false, 403, "PHONE_NOT_NULL"),//手机号码不能为空
    PHONE_FORMAT_ERROR(false, 404, "PHONE_FORMAT_ERROR"),//手机号码格式错误
    BUSINESS_TYPE_BETWEEN(false, 405, "BUSINESS_TYPE_BETWEEN"),//业务类型的值在0到2之间
    BUSINESS_NOT_NULL(false, 406, "BUSINESS_NOT_NULL"),//业务类型不能为空
    PROJECT_NAME_NOT_NULL(false, 407, "PROJECT_NAME_NOT_NULL");//项目名称不能为空

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
