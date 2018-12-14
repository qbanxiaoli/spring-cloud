package com.qbanxiaoli.tool.enums;

import com.qbanxiaoli.common.enums.response.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:30
 */
@Getter
@AllArgsConstructor
public enum SmsResponseEnum implements ResponseEnum {

    MSG_SEND_SUCCESS(true, 201, "MSG_SEND_SUCCESS"),//短信验证码发送成功
    MSG_SEND_FAILURE(false, 401, "MSG_SEND_FAILURE"),//短信验证码发送失败
    MSG_SAVE_FAILURE(false, 402, "MSG_SAVE_FAILURE"),//短信保存失败
    TEMPLATE_NOT_FOUND(false, 403, "TEMPLATE_NOT_FOUND"),//未找到对应短信模板
    PHONE_NOT_NULL(false, 404, "PHONE_NOT_NULL"),//手机号码不能为空
    PHONE_FORMAT_ERROR(false, 405, "PHONE_FORMAT_ERROR"),//手机号码格式错误
    BUSINESS_TYPE_BETWEEN(false, 406, "BUSINESS_TYPE_BETWEEN"),//业务类型的值在0到2之间
    BUSINESS_NOT_NULL(false, 407, "BUSINESS_NOT_NULL"),//业务类型不能为空
    PROJECT_NAME_NOT_NULL(false, 408, "PROJECT_NAME_NOT_NULL"),//项目名称不能为空
    FILE_CONTENT_TYPE_ERROR(false, 409, "FILE_CONTENT_TYPE_ERROR");//上传文件类型错误

    private Boolean result;
    private Integer code;
    private String message;

}
