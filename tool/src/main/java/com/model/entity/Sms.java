package com.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 11:18
 */
@Getter
@Setter
@Entity(name = "alidayu_sms")
@Table(appliesTo = "alidayu_sms", comment = "短信记录表")
@ApiModel(value = "短信记录模型")
public class Sms extends GmtEntity {

    @Column(nullable = false, columnDefinition = "char(11) COMMENT '手机号'")
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @Column(nullable = false, columnDefinition = "tinyint COMMENT '业务类型'")
    @ApiModelProperty(value = "业务类型", required = true)
    private Integer type;

    @Column(nullable = false, columnDefinition = "char(6) COMMENT '短信验证码'")
    @ApiModelProperty(value = "短信验证码", required = true)
    private String captcha;

    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '请求ID'")
    @ApiModelProperty(value = "请求ID", required = true)
    private String requestId;

    @Column(columnDefinition = "varchar(50) COMMENT '发送回执ID,可根据该ID查询具体的发送状态'")
    @ApiModelProperty(value = "发送回执ID,可根据该ID查询具体的发送状态", required = true)
    private String bizId;

    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '状态码-返回OK代表请求成功,其他错误码详见错误码列表'")
    @ApiModelProperty(value = "状态码-返回OK代表请求成功,其他错误码详见错误码列表", required = true)
    private String code;

    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '状态码的描述'")
    @ApiModelProperty(value = "状态码的描述", required = true)
    private String message;

}
