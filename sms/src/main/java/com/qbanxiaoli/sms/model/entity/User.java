package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;
import com.qbanxiaoli.common.model.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/14 11:19
 */
@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户模型")
public class User extends IdEntity {

    @ApiModelProperty(value = "手机号", required = true)
    @Column(nullable = false, length = 20)
    private String phone;

    @ApiModelProperty(value = "密码", required = true)
    @Column(nullable = false, length = 50)
    private String password;

}
