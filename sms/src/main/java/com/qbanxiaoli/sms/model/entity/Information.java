package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 11:18
 */
@Getter
@Setter
@Entity
@Table(name = "alidayu_information", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "projectName"})})
@ApiModel(value = "用户信息模型")
public class Information extends IdEntity {

    @Column(nullable = false)
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    @Column(nullable = false, length = 20)
    @ApiModelProperty(value = "阿里大于accessKeyId", required = true)
    private String accessKeyId;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(value = "阿里大于accessKeySecret", required = true)
    private String accessKeySecret;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;

}
