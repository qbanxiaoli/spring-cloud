package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 11:18
 */
@Data
@Entity
@Table(name = "template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "短信模板模型")
public class Template extends IdEntity {

    @ApiModelProperty(value = "用户信息id", required = true)
    @Column(nullable = false)
    private Long informationId;

    @ApiModelProperty(value = "阿里大于accessKeyId", required = true)
    @Column(nullable = false, length = 20)
    private String accessKeyId;

    @ApiModelProperty(value = "阿里大于accessKeySecret", required = true)
    @Column(nullable = false, length = 30)
    private String accessKeySecret;

    @ApiModelProperty(value = "项目名称", required = true)
    @Column(nullable = false, length = 30)
    private String projectName;

    @ApiModelProperty(value = "短信签名", required = true)
    @Column(nullable = false, length = 20)
    private String signName;

    @ApiModelProperty(value = "短信模板", required = true)
    @Column(nullable = false, length = 20)
    private String templateCode;

    @ApiModelProperty(value = "业务类型", required = true)
    @Column(nullable = false)
    private Integer type;

}
