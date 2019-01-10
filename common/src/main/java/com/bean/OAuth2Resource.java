package com.bean;

import com.enums.oauth2.GrantTypeEnum;
import com.enums.oauth2.ParameterEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/21 4:39 PM
 */
@Component
public class OAuth2Resource {

    private static RestTemplate restTemplate;

    private static OAuth2ClientProperties oAuth2ClientProperties;

    private static OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Autowired
    public void setOAuth2Resource(RestTemplate restTemplate,
                                  OAuth2ClientProperties oAuth2ClientProperties,
                                  OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails) {
        OAuth2Resource.restTemplate = restTemplate;
        OAuth2Resource.oAuth2ClientProperties = oAuth2ClientProperties;
        OAuth2Resource.oAuth2ProtectedResourceDetails = oAuth2ProtectedResourceDetails;

    }

    public static ResponseEntity<OAuth2AccessToken> loginByPassword(String username, String password) {
        //Http Basic 验证
        String clientAndSecret = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
        //这里需要注意为 Basic 而非 Bearer
        clientAndSecret = ParameterEnum.BASIC.getValue() + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, clientAndSecret);
        //授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put(ParameterEnum.USERNAME.getValue(), Collections.singletonList(username));
        map.put(ParameterEnum.PASSWORD.getValue(), Collections.singletonList(password));
        map.put(ParameterEnum.GRANT_TYPE.getValue(), Collections.singletonList(GrantTypeEnum.PASSWORD.getValue()));
        //HttpEntity
        HttpEntity httpEntity = new HttpEntity<>(map, httpHeaders);
        //获取 Token
        return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST, httpEntity, OAuth2AccessToken.class);
    }

    public static ResponseEntity<OAuth2AccessToken> loginByCaptcha(String username, String code) {
        //Http Basic 验证
        String clientAndSecret = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
        //这里需要注意为 Basic 而非 Bearer
        clientAndSecret = ParameterEnum.BASIC.getValue() + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, clientAndSecret);
        //授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put(ParameterEnum.USERNAME.getValue(), Collections.singletonList(username));
        map.put(ParameterEnum.CODE.getValue(), Collections.singletonList(code));
        map.put(ParameterEnum.GRANT_TYPE.getValue(), Collections.singletonList(GrantTypeEnum.SMS_CODE.getValue()));
        //HttpEntity
        HttpEntity httpEntity = new HttpEntity<>(map, httpHeaders);
        //获取 Token
        return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST, httpEntity, OAuth2AccessToken.class);
    }

    public static ResponseEntity<OAuth2AccessToken> refreshToken(String refreshToken) {
        //Http Basic 验证
        String clientAndSecret = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
        //这里需要注意为 Basic 而非 Bearer
        clientAndSecret = ParameterEnum.BASIC.getValue() + Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, clientAndSecret);
        //授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put(OAuth2AccessToken.REFRESH_TOKEN, Collections.singletonList(refreshToken));
        map.put(ParameterEnum.GRANT_TYPE.getValue(), Collections.singletonList(OAuth2AccessToken.REFRESH_TOKEN));
        //HttpEntity
        HttpEntity httpEntity = new HttpEntity<>(map, httpHeaders);
        //获取 Token
        return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST, httpEntity, OAuth2AccessToken.class);
    }

}
