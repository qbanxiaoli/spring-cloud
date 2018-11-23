package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/8/12 下午6:18
 */
@Configuration
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //认证管理器
    private final AuthenticationManager authenticationManager;

    //用户信息相关的实现
    private final UserDetailsService userDetailsService;

    //客户端信息
    private final DataSource dataSource;

    @Autowired
    public OAuth2AuthorizationServerConfig(AuthenticationManager authenticationManager,
                                           UserDetailsService userDetailsService,
                                           DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //客户端详情保存位置
    @Bean
    public JdbcClientDetailsService getJdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // 使用JdbcClientDetailsService客户端详情服务
//        clients.withClientDetails(getJdbcClientDetailsService());
        clients.inMemory()
                .withClient("client1")
                .secret("passwordforauthserver")
                .redirectUris("http://localhost:8080/")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("myscope")
                .autoApprove(true)
                .accessTokenValiditySeconds(30)
                .refreshTokenValiditySeconds(1800)
                .and()
                .withClient("client2")
                .secret("passwordforauthserver")
                .redirectUris("http://localhost:8081/")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("myscope")
                .autoApprove(true)
                .accessTokenValiditySeconds(30)
                .refreshTokenValiditySeconds(1800);
    }

    //配置认证管理器以及用户信息业务实现
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtAccessTokenConverter())
                // 配置tokenStore,需要配置userDetailsService，否则refresh_token会报错
                .userDetailsService(userDetailsService)
                .tokenStore(jwtTokenStore());
    }

    //配置认证规则，那些需要认证那些不需要
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    // 使用非对称加密算法来对Token进行签名
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("tcm.jks"), "tcmpass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("tcm"));
        return converter;
    }

}
