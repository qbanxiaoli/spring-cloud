package com.qbanxiaoli.common.model.vo;

import com.qbanxiaoli.common.bean.I18nResource;
import com.qbanxiaoli.common.enums.ResponseEnum;
import lombok.Data;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/3 13:31
 */
@Data
public class ResponseVO<T> {

    /** 结果状态 */
    private Boolean result;
    /** 结果码 */
    private Integer code;
    /** 结果信息 */
    private String message;
    /** 返回数据 */
    private T data;

    //返回结果不包含数据，返回信息不包含变量
    public ResponseVO(ResponseEnum responseEnum) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage());
    }

    //返回结果不包含数据，返回信息包含变量
    public ResponseVO(ResponseEnum responseEnum, Object[] args) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage(), args);
    }

    //返回结果包含数据，返回信息不包含变量
    public ResponseVO(ResponseEnum responseEnum, T data) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage());
        this.data = data;
    }

    //返回结果包含数据，返回信息包含变量
    public ResponseVO(ResponseEnum responseEnum, T data, Object[] args) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage(), args);
        this.data = data;
    }

}