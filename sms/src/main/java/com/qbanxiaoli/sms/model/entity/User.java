package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 11:19
 */
@Getter
@Setter
@Entity
@Table(name = "user")
@ApiModel(value = "用户模型")
public class User extends IdEntity {

    @Column(nullable = false, length = 20)
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @Column(nullable = false, length = 50)
    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
