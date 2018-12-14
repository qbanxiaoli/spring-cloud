package com.qbanxiaoli.common.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbanxiaoli.common.model.vo.JwtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/14 10:44
 */
@Component
public class JwtResource {

    private static HttpServletRequest request;

    @Autowired
    public void setJwtResource(HttpServletRequest request) {
        JwtResource.request = request;
    }

    public static JwtVO parseJwt() {
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = new OAuth2AuthenticationDetails(request);
        Jwt jwt = JwtHelper.decode(oAuth2AuthenticationDetails.getTokenValue());
        ObjectMapper objectMapper = new ObjectMapper();
        JwtVO jwtVO = null;
        try {
            jwtVO = objectMapper.readValue(jwt.getClaims(), JwtVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jwtVO;
    }

    public static String getUserId() {
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = new OAuth2AuthenticationDetails(request);
        Jwt jwt = JwtHelper.decode(oAuth2AuthenticationDetails.getTokenValue());
        ObjectMapper objectMapper = new ObjectMapper();
        JwtVO jwtVO = null;
        try {
            jwtVO = objectMapper.readValue(jwt.getClaims(), JwtVO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jwtVO != null) {
            return jwtVO.getUuid();
        }
        return null;
    }

}
