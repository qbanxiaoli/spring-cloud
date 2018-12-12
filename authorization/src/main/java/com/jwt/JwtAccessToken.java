package com.jwt;

import com.entity.Role;
import com.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/11/22 20:56
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        //设置过期时间
        defaultOAuth2AccessToken.setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 24));
        // 设置额外用户信息
        String uuid = ((User) authentication.getPrincipal()).getUuid();
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put("uuid", uuid);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

}