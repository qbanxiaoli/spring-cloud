package com.bean;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author qbanxiaoli
 * @description 语言国际化工具
 * @create 2018/5/31 10:12
 */
@Component
public class I18nResource implements MessageSourceAware {

    private static MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        I18nResource.messageSource = messageSource;
    }

    /**
     * @param message 消息所对应的变量名
     * @return 返回不包含变量的对应语言的消息
     * @author qbanxiaoli
     * @description 从配置文件中找到message对应语言的消息
     */
    public static String getMessage(String message) {
        //获取当前请求的语言类型
        Locale locale = LocaleContextHolder.getLocale();
        //返回配置文件中找到message对应语言的消息
        //如果在指定的locale中没有找到消息，则使用默认的消息。
        return messageSource.getMessage(message, null, message, locale);
    }

    /**
     * @param message 消息所对应的变量名
     * @return 返回包含变量的对应语言的消息
     * @author qbanxiaoli
     * @description 从配置文件中找到message对应语言的消息
     */
    public static String getMessage(String message, Object[] args) {
        //获取当前请求的语言类型
        Locale locale = LocaleContextHolder.getLocale();
        //返回配置文件中找到message对应语言的消息
        //如果在指定的locale中没有找到消息，则使用默认的消息。args中的参数将使用标准类库中的MessageFormat来作消息中替换值。
        return messageSource.getMessage(message, args, message, locale);
    }

}
