package com.model.vo;

import com.bean.I18nResource;
import com.enums.response.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/3 13:31
 */
@Data
@ApiModel(value = "数据返回模型")
public class ResponseVO<T> {

    @ApiModelProperty(value = "结果状态", required = true)
    private Boolean result;

    @ApiModelProperty(value = "结果码", required = true)
    private Integer code;

    @ApiModelProperty(value = "结果信息", required = true)
    private String message;

    @ApiModelProperty(value = "返回数据", required = true)
    private T data;

    //返回结果不包含数据，返回信息不包含变量
    public ResponseVO(ResponseEnum responseEnum) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage());
    }

    //返回结果包含数据，返回信息不包含变量
    public ResponseVO(ResponseEnum responseEnum, T data) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage());
        this.data = data;
    }

    //返回结果不包含数据，返回信息包含变量
    public ResponseVO(ResponseEnum responseEnum, Object[] args) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage(), args);
    }

    //返回结果包含数据，返回信息包含变量
    public ResponseVO(ResponseEnum responseEnum, T data, Object[] args) {
        this.result = responseEnum.getResult();
        this.code = responseEnum.getCode();
        this.message = I18nResource.getMessage(responseEnum.getMessage(), args);
        this.data = data;
    }

}