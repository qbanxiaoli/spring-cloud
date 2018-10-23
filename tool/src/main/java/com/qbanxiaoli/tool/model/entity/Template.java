package com.qbanxiaoli.tool.model.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
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
@Entity(name = "alidayu_template")
@Table(appliesTo = "alidayu_template", comment = "阿里大于短信模版表")
@ApiModel(value = "短信模板模型")
public class Template extends IdEntity {

    @Column(nullable = false, columnDefinition = "char(16) COMMENT '阿里大于accessKeyId'")
    @ApiModelProperty(value = "阿里大于accessKeyId", required = true)
    private String accessKeyId;

    @Column(nullable = false, columnDefinition = "char(30) COMMENT '阿里大于accessKeySecret'")
    @ApiModelProperty(value = "阿里大于accessKeySecret", required = true)
    private String accessKeySecret;

    @Column(nullable = false, columnDefinition = "varchar(10) COMMENT '短信签名'")
    @ApiModelProperty(value = "短信签名", required = true)
    private String signName;

    @Column(nullable = false, columnDefinition = "varchar(15) COMMENT '短信模板'")
    @ApiModelProperty(value = "短信模板", required = true)
    private String templateCode;

    @Column(nullable = false, columnDefinition = "tinyint COMMENT '业务类型(0->注册，1->登录，2->修改密码)'")
    @ApiModelProperty(value = "业务类型(0->注册，1->登录，2->修改密码)", required = true)
    private Integer type;

}
