package com.qbanxiaoli.common.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/3 13:54
 */
@Getter
@Setter
@ApiModel(value = "分页查询请求模型")
public class QueryDTO {

    @Min(1)
    @ApiModelProperty(value = "当前页")
    private Integer currentPage = 1;

    @Min(1)
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize = 10;

}
