package com.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;
import com.qbanxiaoli.common.model.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/11 19:25
 */
@Getter
@Setter
@Entity
@Table(appliesTo = "user", comment = "用户表")
public class User extends GmtEntity implements UserDetails {

    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '用户名'")
    private String username;

    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '密码'")
    private String password;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '账户未过期'")
    private boolean accountNonExpired = true;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '账户非锁定'")
    private boolean accountNonLocked = true;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '凭证未过期'")
    private boolean credentialsNonExpired = true;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '是否启用'")
    private boolean enabled = true;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

}
