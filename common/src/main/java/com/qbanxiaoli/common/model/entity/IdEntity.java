package com.qbanxiaoli.common.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by qbanxiaoli on 2017/9/5.
 */
@Data
@MappedSuperclass
@ApiModel(value = "主键模型")
class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "自增主键", required = true)
    private Long id;

}
