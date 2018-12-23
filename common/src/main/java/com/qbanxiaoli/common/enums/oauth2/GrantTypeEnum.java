package com.qbanxiaoli.common.enums.oauth2;

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

    PASSWORD("password", "密码模式"),
    SMS_CODE("sms_code", "短信验证模式");

    private String value;

    private String description;

}
