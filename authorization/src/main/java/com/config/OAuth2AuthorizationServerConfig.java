package com.config;

import com.jwt.JwtAccessToken;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/8/12 下午6:18
 */
@Configuration
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final UserService userService;

    //认证管理器
    private final AuthenticationManager authenticationManager;

    //客户端信息
    private final DataSource dataSource;

    @Autowired
    public OAuth2AuthorizationServerConfig(UserService userService,
                                           AuthenticationManager authenticationManager,
                                           DataSource dataSource) {
        this.userService = userService;
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
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("tcm.jks"), "tcmpass".toCharArray());
        jwtAccessToken.setKeyPair(keyStoreKeyFactory.getKeyPair("tcm"));
        return jwtAccessToken;
    }

    //配置认证管理器以及用户信息业务实现
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtAccessToken())
                // 配置tokenStore,需要配置userDetailsService，否则refresh_token会报错
                .userDetailsService(userService);
    }

    //配置认证规则，哪些需要认证哪些不需要
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

}
