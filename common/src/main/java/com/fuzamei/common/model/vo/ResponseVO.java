package com.fuzamei.common.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fuzamei.common.bean.I18nResource;
import com.fuzamei.common.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/3 13:31
 */
@Data
public class ResponseVO<T> {

    /** 结果状态 */
    private String success;

    /** 结果信息 */
    private String message;

    /** 结果码 */
    private String code;

    /** 返回数据 */
    private T data;

    //返回结果不包含数据，返回信息不包含变量
    public ResponseVO(ResponseEnum responseEnum) {
        this.message = I18nResource.getMessage(responseEnum.getMessage());
        this.success = responseEnum.getSuccess();
        this.code = responseEnum.getCode();
    }
    //返回结果不包含数据，返回信息不包含变量
    public ResponseVO(ResponseEnum responseEnum, String message) {
        this.message = I18nResource.getMessage(message);
        this.success = responseEnum.getSuccess();
        this.code = responseEnum.getCode();
    }

    //返回结果不包含数据，返回信息包含变量
    public ResponseVO(ResponseEnum responseEnum, Object[] args) {
        this.message = I18nResource.getMessage(responseEnum.getMessage(), args);
        this.success = responseEnum.getSuccess();
        this.code = responseEnum.getCode();
    }

    //返回结果包含数据，返回信息不包含变量
    public ResponseVO(ResponseEnum responseEnum, T data) {
        this.message = I18nResource.getMessage(responseEnum.getMessage());
        this.success = responseEnum.getSuccess();
        this.code = responseEnum.getCode();
        this.data = data;
    }

    //返回结果包含数据，返回信息包含变量
    public ResponseVO(ResponseEnum responseEnum, T data, Object[] args) {
        this.message = I18nResource.getMessage(responseEnum.getMessage(), args);
        this.success = responseEnum.getSuccess();
        this.code = responseEnum.getCode();
        this.data = data;
    }



}