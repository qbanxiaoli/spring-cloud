package com.config;

import com.filter.JwtCheckGatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/11/22 18:49
 */
@Configuration
public class JwtConfig {

    @Bean
    public JwtCheckGatewayFilter jwtCheckGatewayFilterFactory(){
        return new JwtCheckGatewayFilter();
    }

}