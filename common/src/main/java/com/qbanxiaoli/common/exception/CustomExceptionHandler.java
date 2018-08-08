package com.qbanxiaoli.common.exception;

import com.qbanxiaoli.common.enums.CommonResponseEnum;
import com.qbanxiaoli.common.model.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Q版小李
 * @description 统一异常处理
 * @create 2018/8/8 11:35
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO handleBindException(MethodArgumentNotValidException e) {
        for (FieldError errors : e.getBindingResult().getFieldErrors()) {
            log.info("参数错误：" + errors.getDefaultMessage());
        }
        return new ResponseVO<>(CommonResponseEnum.PARAMETER_ERROR, e.getBindingResult().getFieldErrors());
    }

    @ExceptionHandler(BindException.class)
    public ResponseVO handleBindException(BindException e) {
        for (FieldError errors : e.getBindingResult().getFieldErrors()) {
            log.info("参数错误：" + errors.getDefaultMessage());
        }
        return new ResponseVO<>(CommonResponseEnum.PARAMETER_ERROR, e.getBindingResult().getFieldErrors());
    }

}
