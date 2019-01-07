package com.qbanxiaoli.common.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/22 1:37 AM
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //限制上传单个文件大小
        factory.setMaxFileSize("50MB");
        //限制上传的总数据大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }

}
