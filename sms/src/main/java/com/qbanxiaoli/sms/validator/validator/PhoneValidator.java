package com.qbanxiaoli.sms.validator.validator;

import com.qbanxiaoli.sms.validator.Phone;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/5 17:31
 */
public class PhoneValidator implements ConstraintValidator<Phone,String> {

    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //为空 不校验
        if (value==null){
            return true;
        }
        String phoneReg = "^(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?$";
        return Pattern.matches(phoneReg,value);
    }
}
