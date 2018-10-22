package com.qbanxiaoli.sms.model.entity;

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
 * @author Q版小李
 * @description
 * @create 2018/10/20 17:43
 */
@Getter
@Setter
@Entity
@Table(appliesTo = "supplier_bill", comment = "供应商白条表")
@ApiModel(value = "供应商白条模型")
public class SupplierBill extends GmtEntity {

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '公司id'")
    @ApiModelProperty(value = "供应商id", required = true)
    private String supplierId;

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '餐厅id'")
    @ApiModelProperty(value = "餐厅管理员id", required = true)
    private String restaurantId;

    @Column(nullable = false, columnDefinition = "varchar(32) COMMENT '白条编号'")
    @ApiModelProperty(value = "白条编号", required = true)
    private String number;

    @Column(nullable = false, columnDefinition = "decimal(10,2) unsigned COMMENT '白条金额'")
    @ApiModelProperty(value = "白条金额", required = true)
    private BigDecimal amount;

    @Column(nullable = false, columnDefinition = "decimal(10,2) unsigned DEFAULT '0' COMMENT '已兑付金额'")
    @ApiModelProperty(value = "已兑付金额", required = true)
    private BigDecimal paidAmount;

    @Column(nullable = false, columnDefinition = "tinyint COMMENT '白条状态(0->尚未申请兑付，1->待公司兑付，2->已拒绝，3->待确认受到兑付，4->已完成)'")
    @ApiModelProperty(value = "白条状态", required = true)
    private Integer status;

    @Column(nullable = false, columnDefinition = "bigint COMMENT '承兑日期'")
    @ApiModelProperty(value = "承兑日期", required = true)
    private Long promiseTime;

}
