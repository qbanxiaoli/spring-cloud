package com.qbanxiaoli.common.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/3 13:54
 */
@Data
@ApiModel(value = "分页查询请求模型")
public class QueryDTO {

    @ApiModelProperty(value = "每页记录数", required = true)
    private Long pageSize;

    @ApiModelProperty(value = "当前页", required = true)
    private Long currentPage;

}
