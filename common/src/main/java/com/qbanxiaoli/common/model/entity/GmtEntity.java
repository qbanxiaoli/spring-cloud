package com.qbanxiaoli.common.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;


/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:06
 */
@Getter
@Setter
@MappedSuperclass
@ApiModel(value = "时间模型")
public class GmtEntity extends IdEntity {

    @Column(nullable = false, columnDefinition = "bigint COMMENT '创建时间'")
    @ApiModelProperty(value = "创建时间", required = true)
    private Long gmtCreated;

    @Column(nullable = false, columnDefinition = "bigint COMMENT '修改时间'")
    @ApiModelProperty(value = "修改时间", required = true)
    private Long gmtModified;

    /**
     * 注解说明：
     *
     * @PrePersist 和 @PostPersist 事件的触发由保存实体引起。
     * @PrePersist 事件在调用EntityManager.persist()方法后立刻发生，级联保存也会发生此事件，此时的数据还没有真实插入进数据库。
     * @PostPersist 事件在数据已经插入进数据库后发生。
     */
    @PrePersist
    public void prePersist() {
        if (this.getUuid() == null) {
            this.setUuid(UUID.randomUUID().toString().replace("-", ""));
        }
        this.setGmtCreated(System.currentTimeMillis());
        this.setGmtModified(System.currentTimeMillis());
    }

    /**
     * 注解说明：
     *
     * @PreUpdate 和 @PostUpdate 事件的触发由更新实体引起。
     * @PreUpdate 事件在实体的状态同步到数据库之前触发，此时的数据还没有真实更新到数据库。
     * @PostUpdate 事件在实体的状态同步到数据库后触发，同步在事务提交时发生。
     */
    @PreUpdate
    public void preUpdate() {
        this.setGmtModified(System.currentTimeMillis());
    }

}
