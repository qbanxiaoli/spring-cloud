package com.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/16 18:27
 */
@Getter
@AllArgsConstructor
public enum GrantTypeEnum {

    SMS_CODE("sms_code", "短信验证模式"),
    EMAIL_CODE("email_code", "邮箱验证模式");

    private String name;

    private String description;

}
