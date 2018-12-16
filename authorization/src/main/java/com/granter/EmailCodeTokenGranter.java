package com.granter;

import com.bean.UserResource;
import com.entity.User;
import com.enums.ParameterEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/14 17:03
 */
@Slf4j
public class EmailCodeTokenGranter extends AbstractTokenGranter {

    public EmailCodeTokenGranter(AuthorizationServerTokenServices tokenServices,
                                 ClientDetailsService clientDetailsService,
                                 OAuth2RequestFactory requestFactory,
                                 String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,
                                                           TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String email = parameters.get(ParameterEnum.EMAIL.getName());  //客户端提交的手邮箱号
        String code = parameters.get(ParameterEnum.CODE.getName());  //客户端提交的验证码

        if (StringUtils.isBlank(email)) {
            throw new InvalidGrantException("邮箱号码不能为空");
        }
        if (StringUtils.isBlank(code)) {
            throw new InvalidGrantException("验证码不能为空");
        }

        User user = UserResource.findByEmail(email);

        // 验证验证码
//        if (true) {
//            throw new InvalidGrantException("验证码错误");
//        }

        log.info("邮箱验证通过，登录成功");
        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }

}
