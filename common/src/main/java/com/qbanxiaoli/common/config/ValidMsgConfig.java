package com.qbanxiaoli.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 /**
 * @author qbanxiaoli
 * @description 验证信息国际化
 * @create 2018/5/9 18:55
 */
 @Configuration
 public class ValidMsgConfig implements WebMvcConfigurer {

     @Override
     public Validator getValidator() {
         LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
         validator.setValidationMessageSource(getMessageSource());
         return validator;
     }

     private ResourceBundleMessageSource getMessageSource() {
         ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
         messageSource.setDefaultEncoding("UTF-8");
         messageSource.setBasenames("i18n/messages");
         messageSource.setCacheSeconds(10);
         return messageSource;
     }
 }