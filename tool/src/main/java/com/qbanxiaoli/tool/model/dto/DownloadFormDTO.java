package com.qbanxiaoli.tool.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/22 18:42
 */
@Data
@ApiModel("文件下载数据传输模型")
public class DownloadFormDTO {

    @NotBlank
    @ApiModelProperty(value = "文件访问地址", required = true)
    private String fileUrl;

}
