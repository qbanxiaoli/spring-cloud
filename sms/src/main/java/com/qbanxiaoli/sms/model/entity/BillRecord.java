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
 * @create 2018/10/20 16:33
 */
@Getter
@Setter
@Entity
@Table(appliesTo = "bill_record", comment = "白条记录表")
@ApiModel(value = "白条记录模型")
public class BillRecord extends GmtEntity {

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '用户id'")
    @ApiModelProperty(value = "用户id", required = true)
    private String userId;

    @Column(nullable = false, columnDefinition = "varchar(20) COMMENT '用户名称'")
    @ApiModelProperty(value = "用户名称", required = true)
    private String username;

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '对方用户id'")
    @ApiModelProperty(value = "对方用户id", required = true)
    private String oppositeId;

    @Column(nullable = false, columnDefinition = "varchar(20) COMMENT '对方用户名称'")
    @ApiModelProperty(value = "对方用户名称", required = true)
    private String oppositeName;

    @Column(nullable = false, columnDefinition = "varchar(32) COMMENT '白条编号'")
    @ApiModelProperty(value = "白条编号", required = true)
    private String number;

    @Column(columnDefinition = "varchar(32) COMMENT '订单编号'")
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @Column(nullable = false, columnDefinition = "decimal(10,2) unsigned COMMENT '白条金额'")
    @ApiModelProperty(value = "白条金额", required = true)
    private BigDecimal amount;

    @Column(nullable = false, columnDefinition = "tinyint COMMENT '业务类型(0->支付白条，1->兑付白条，2->转账白条)'")
    @ApiModelProperty(value = "业务类型", required = true)
    private Integer type;

    @Column(nullable = false, columnDefinition = "tinyint COMMENT '白条状态(-1->冻结中，0->尚未申请兑付，1->待公司兑付，2->已拒绝，3->待确认受到兑付，4->已完成)'")
    @ApiModelProperty(value = "白条状态", required = true)
    private Integer status;

    @Column(nullable = false, columnDefinition = "bigint COMMENT '承兑日期'")
    @ApiModelProperty(value = "承兑日期", required = true)
    private Long promiseTime;

    @Column(columnDefinition = "bigint COMMENT '申请承兑日期'")
    @ApiModelProperty(value = "申请承兑日期")
    private Long applyTime;

    @Column(columnDefinition = "bigint COMMENT '已兑付日期'")
    @ApiModelProperty(value = "已兑付日期")
    private Long payTime;

    @Column(columnDefinition = "bigint COMMENT '收到兑付日期'")
    @ApiModelProperty(value = "收到兑付日期")
    private Long confirmTime;

}
