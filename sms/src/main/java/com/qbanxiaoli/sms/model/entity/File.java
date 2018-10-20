package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:01 PM
 */
@Getter
@Setter
@Entity
@ApiModel(value = "用户信息模型")
public class File extends IdEntity {

    @Column(nullable = false)
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;

    @Column(nullable = false, length = 50)
    @ApiModelProperty(value = "文件路径", required = true)
    private String path;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(value = "阿里大于accessKeySecret", required = true)
    private String accessKeySecret;

    @Column(nullable = false, length = 30)
    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;

}

