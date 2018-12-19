package com.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/19 5:12 PM
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
class CustomOauthException extends OAuth2Exception {

    CustomOauthException(String msg) {
        super(msg);
    }

}
