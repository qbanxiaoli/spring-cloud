package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 11:18
 */
@Getter
@Setter
@Entity
@Table(name = "alidayu_template")
@ApiModel(value = "短信模板模型")
public class Template extends IdEntity {

    @Column(nullable = false)
    @ApiModelProperty(value = "用户信息id", required = true)
    private Long informationId;

    @Column(nullable = false, length = 20)
    @ApiModelProperty(value = "阿里大于accessKeyId", required = true)
    private String accessKeyId;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(value = "阿里大于accessKeySecret", required = true)
    private String accessKeySecret;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;

    @Column(nullable = false, length = 20)
    @ApiModelProperty(value = "短信签名", required = true)
    private String signName;

    @Column(nullable = false, length = 20)
    @ApiModelProperty(value = "短信模板", required = true)
    private String templateCode;

    @Column(nullable = false)
    @ApiModelProperty(value = "业务类型", required = true)
    private Integer type;

}
