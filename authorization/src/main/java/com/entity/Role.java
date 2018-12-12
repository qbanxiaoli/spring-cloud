package com.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/12 15:40
 */
@Getter
@Setter
@Entity
@Table(appliesTo = "role", comment = "权限表")
public class Role extends GmtEntity implements GrantedAuthority {

    @Column(nullable = false, updatable = false, columnDefinition = "char(32) COMMENT '用户id'")
    private String userId;

    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '用户权限'")
    private String authority;

}
