package com.qbanxiaoli.common.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Q版小李
 * @description
 * @create 2018/10/26 12:38
 */
@Getter
@Setter
@ApiModel(value = "关键字查询请求模型")
public class KeywordQueryDTO extends GmtQueryDTO {

    @ApiModelProperty(value = "关键字")
    private Long keyword;

}
