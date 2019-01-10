package com.enums.oauth2;

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

    ROLE("ROLE_", "权限前缀"),
    BASIC("Basic ", "token前缀"),
    USERNAME("username", "用户名"),
    PASSWORD("password", "密码"),
    CODE("code", "验证码"),
    GRANT_TYPE("grant_type", "授权类型");

    private String value;

    private String description;
}
