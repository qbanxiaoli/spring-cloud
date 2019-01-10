package com.exception;

import com.enums.response.CommonResponseEnum;
import com.model.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Q版小李
 * @description 统一异常处理
 * @create 2018/8/8 11:35
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseVO handleServletRequestBindingException(ConstraintViolationException e) {
        log.error("参数校验异常：" + e.getMessage());
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        Map<String, String> map = new HashMap<>(16);
        while (iterator.hasNext()) {
            ConstraintViolation<?> constraintViolation = iterator.next();
            String name = constraintViolation.getPropertyPath().toString();
            log.error(name + ":" + constraintViolation);
            map.put(name.substring(name.indexOf(".") + 1), constraintViolation.getMessage());
        }
        return new ResponseVO<>(CommonResponseEnum.PARAMETER_ERROR, map);
    }

    @ExceptionHandler(BindException.class)
    public ResponseVO handleBindException(BindException e) {
        log.error("参数校验异常：" + e.getMessage());
        Map<String, String> map = new HashMap<>(16);
        for (FieldError errors : e.getBindingResult().getFieldErrors()) {
            log.error(errors.getField() + ":" + errors.getDefaultMessage());
            map.put(errors.getField(), errors.getDefaultMessage());
        }
        return new ResponseVO<>(CommonResponseEnum.PARAMETER_ERROR, map);
    }

}
