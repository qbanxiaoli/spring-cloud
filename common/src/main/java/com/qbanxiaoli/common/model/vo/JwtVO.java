package com.qbanxiaoli.common.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/14 10:47
 */
@Data
@ApiModel(value = "令牌模型")
public class JwtVO {

    @JsonProperty("user_name")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "过期时间", required = true)
    private Long exp;

    @ApiModelProperty(value = "用户id", required = true)
    private String uuid;

    @ApiModelProperty(value = "客户端标识", required = true)
    private String client_id;

    @ApiModelProperty(value = "jwt唯一标识", required = true)
    private String jti;

    @ApiModelProperty(value = "权限列表", required = true)
    private List<String> authorities;

    @ApiModelProperty(value = "权限范围", required = true)
    private List<String> scope;

}
