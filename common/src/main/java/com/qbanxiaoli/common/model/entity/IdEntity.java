package com.qbanxiaoli.common.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:06
 */
@Getter
@Setter
@MappedSuperclass
@ApiModel(value = "主键模型")
public class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint COMMENT '自增主键'")
    @ApiModelProperty(value = "自增主键", required = true)
    private Long id;

    @Column(nullable = false, columnDefinition = "char(32) COMMENT '唯一标识'")
    @ApiModelProperty(value = "唯一标识", required = true)
    private String uuid;

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
    }

}
