package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 11:18
 */
@Data
@Entity
@Table(name = "aliyun_information", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "projectName"})})
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户信息模型")
public class Information extends IdEntity {

    @ApiModelProperty(value = "用户id", required = true)
    @Column(nullable = false)
    private Long userId;

    @ApiModelProperty(value = "阿里大于accessKeyId", required = true)
    @Column(nullable = false, length = 20)
    private String accessKeyId;

    @ApiModelProperty(value = "阿里大于accessKeySecret", required = true)
    @Column(nullable = false, length = 30)
    private String accessKeySecret;

    @ApiModelProperty(value = "项目名称", required = true)
    @Column(nullable = false, length = 30)
    private String projectName;

}
