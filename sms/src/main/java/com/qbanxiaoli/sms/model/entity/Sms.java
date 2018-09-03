package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "aliyun_sms")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "短信模型")
public class Sms extends GmtEntity {

    @ApiModelProperty(value = "发送者id", required = true)
    @Column(nullable = false)
    private Long userId;

    @ApiModelProperty(value = "用户手机号", required = true)
    @Column(nullable = false, length = 20)
    private String phone;

    @ApiModelProperty(value = "业务类型", required = true)
    @Column(nullable = false, length = 2)
    private Integer type;

    @ApiModelProperty(value = "短信验证码", required = true)
    @Column(nullable = false, length = 10)
    private String captcha;

    @ApiModelProperty(value = "项目名称", required = true)
    @Column(nullable = false, length = 30)
    private String projectName;

    @ApiModelProperty(value = "请求ID", required = true)
    @Column(nullable = false, length = 50)
    private String requestId;

    @ApiModelProperty(value = "状态码-返回OK代表请求成功,其他错误码详见错误码列表", required = true)
    @Column(length = 50)
    private String bizId;

    @ApiModelProperty(value = "状态码的描述", required = true)
    @Column(nullable = false, length = 50)
    private String code;

    @ApiModelProperty(value = "发送回执ID,可根据该ID查询具体的发送状态", required = true)
    @Column(nullable = false, length = 50)
    private String message;

}
