package com.qbanxiaoli.sms.validation.validator;

import com.qbanxiaoli.sms.validation.Phone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/5 17:31
 */
@RefreshScope
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Value("${reg.phone}")
    private String phoneReg;

    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //为空 不校验
        return value == null || Pattern.matches(phoneReg, value);
    }

}
