package com.qbanxiaoli.common.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:06
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "时间模型")
public class GmtEntity extends IdEntity {

    @Column(nullable = false)
    @ApiModelProperty(value = "创建时间", required = true)
    private Long gmtCreate;

    @Column(nullable = false)
    @ApiModelProperty(value = "修改时间", required = true)
    private Long gmtModified;

}
