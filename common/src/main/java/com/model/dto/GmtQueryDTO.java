package com.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Q版小李
 * @description
 * @create 2018/10/26 12:31
 */
@Getter
@Setter
@ApiModel(value = "时间分页查询请求模型")
public class GmtQueryDTO extends QueryDTO {

    @ApiModelProperty(value = "开始时间")
    private Long startTime;

    @ApiModelProperty(value = "结束时间")
    private Long endTime;

}
