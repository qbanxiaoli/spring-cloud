package com.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/14 20:36
 */
@Getter
@AllArgsConstructor
public enum ParameterEnum {

    USERNAME("username", "用户名"),
    PHONE("phone", "手机号码"),
    EMAIL("email", "邮箱号码"),
    CODE("code", "验证码");

    private String name;

    private String description;
}
