package com.config;

import com.enums.GrantTypeEnum;
import com.exception.CustomWebResponseExceptionTranslator;
import com.granter.SMSCodeTokenGranter;
import com.jwt.JwtAccessToken;
import com.service.UserDetailsServiceImpl;
import com.granter.EmailCodeTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/8/12 下午6:18
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    private final KeyProperties keyProperties;

    private final UserDetailsServiceImpl userDetailsService;

    //认证管理器
    private final AuthenticationManager authenticationManager;

    //客户端信息
    private final DataSource dataSource;

    @Autowired
    public OAuth2AuthorizationServerConfig(CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator,
                                           KeyProperties keyProperties,
                                           UserDetailsServiceImpl userService,
                                           AuthenticationManager authenticationManager,
                                           DataSource dataSource) {
        this.customWebResponseExceptionTranslator = customWebResponseExceptionTranslator;
        this.keyProperties = keyProperties;
        this.userDetailsService = userService;
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用JdbcClientDetailsService客户端详情服务
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    // 使用非对称加密算法来对Token进行签名
    @Bean
    public JwtAccessToken jwtAccessToken() {
        JwtAccessToken jwtAccessToken = new JwtAccessToken();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyProperties.getKeyStore().getLocation(), keyProperties.getKeyStore().getPassword().toCharArray());
        jwtAccessToken.setKeyPair(keyStoreKeyFactory.getKeyPair(keyProperties.getKeyStore().getAlias()));
        return jwtAccessToken;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessToken());
    }

    //配置认证管理器以及用户信息业务实现
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(jwtTokenStore())
                .tokenGranter(tokenGranter())
                .authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtAccessToken())
                // 配置tokenStore,需要配置userDetailsService，否则refresh_token会报错
                .userDetailsService(userDetailsService)
                .exceptionTranslator(customWebResponseExceptionTranslator);
    }

    //配置认证规则，哪些需要认证哪些不需要
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .passwordEncoder(new BCryptPasswordEncoder())
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    //以下所有部分是对oauth2的授权模式扩展
    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    private AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();  //使用默认
    }

    private OAuth2RequestFactory requestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService());  //使用默认
    }

    private List<TokenGranter> getDefaultTokenGranters() {
        ClientDetailsService clientDetails = clientDetailsService();
        AuthorizationServerTokenServices tokenServices = tokenServices();
        AuthorizationCodeServices authorizationCodeServices = authorizationCodeServices();
        OAuth2RequestFactory requestFactory = requestFactory();
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices,
                authorizationCodeServices, clientDetails, requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
        ImplicitTokenGranter implicit = new ImplicitTokenGranter(tokenServices, clientDetails,
                requestFactory);
        tokenGranters.add(implicit);
        tokenGranters.add(
                new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
        if (authenticationManager != null) {
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager,
                    tokenServices, clientDetails, requestFactory));
        }
        tokenGranters.add(new SMSCodeTokenGranter(tokenServices, clientDetails, requestFactory, GrantTypeEnum.SMS_CODE.getName()));
        tokenGranters.add(new EmailCodeTokenGranter(tokenServices, clientDetails, requestFactory, GrantTypeEnum.EMAIL_CODE.getName()));
        return tokenGranters;
    }

    private TokenGranter tokenGranter() {
        return new TokenGranter() {
            private CompositeTokenGranter delegate;

            @Override
            public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
                if (delegate == null) {
                    delegate = new CompositeTokenGranter(getDefaultTokenGranters());
                }
                return delegate.grant(grantType, tokenRequest);
            }
        };
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(jwtAccessToken()));
        return tokenEnhancerChain;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(jwtTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(tokenEnhancer());   // 如果没有设置它,JWT就失效了.
        return defaultTokenServices;
    }

}
