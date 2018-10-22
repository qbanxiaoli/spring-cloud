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
 * @create 2018/10/20 17:41
 */
@Getter
@Setter
@Entity
@Table(appliesTo = "restaurant_bill", comment = "餐厅白条表")
@ApiModel(value = "餐厅白条模型")
public class RestaurantBill extends GmtEntity {

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '公司id'")
    @ApiModelProperty(value = "公司管理员id", required = true)
    private String companyId;

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '餐厅id'")
    @ApiModelProperty(value = "餐厅管理员id", required = true)
    private String restaurantId;

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
