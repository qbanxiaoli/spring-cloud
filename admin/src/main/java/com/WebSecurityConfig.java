package com;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/8/10 上午4:10
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 调用父类构造函数，开启表单认证和http认证
        super.configure(http);
        // 关闭csrf保护
        http.csrf().disable();
    }

}