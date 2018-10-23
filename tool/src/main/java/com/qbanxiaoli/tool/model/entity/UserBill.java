package com.qbanxiaoli.tool.model.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/23 11:20
 */
@Getter
@Setter
@Entity
@Table(appliesTo = "user_bill", comment = "用户白条表")
@ApiModel(value = "公司白条模型")
public class UserBill extends GmtEntity {

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '用户id'")
    @ApiModelProperty(value = "用户id", required = true)
    private String userId;

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '白条编号'")
    @ApiModelProperty(value = "白条编号", required = true)
    private String number;

    @Column(nullable = false, columnDefinition = "decimal(10,2) unsigned COMMENT '白条金额'")
    @ApiModelProperty(value = "白条金额", required = true)
    private BigDecimal amount;

    @Column(nullable = false, columnDefinition = "decimal(10,2) unsigned DEFAULT '0' COMMENT '已兑付金额'")
    @ApiModelProperty(value = "已兑付金额", required = true)
    private BigDecimal paidAmount;

}
