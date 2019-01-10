package com.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/8/9 上午3:04
 */
@Data
@ApiModel(value = "短信请求返回模型")
public class SendSmsResponseVO {

    @ApiModelProperty(value = "请求ID", required = true)
    private String requestId;

    @ApiModelProperty(value = "发送回执ID,可根据该ID查询具体的发送状态")
    private String bizId;

    @ApiModelProperty(value = "状态码-返回OK代表请求成功,其他错误码详见错误码列表", required = true)
    private String code;

    @ApiModelProperty(value = "状态码的描述", required = true)
    private String message;

}
