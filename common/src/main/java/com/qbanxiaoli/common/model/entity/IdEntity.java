package com.qbanxiaoli.common.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:06
 */
@Getter
@Setter
@MappedSuperclass
@ApiModel(value = "主键模型")
public class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "自增主键", required = true)
    private Long id;

}
