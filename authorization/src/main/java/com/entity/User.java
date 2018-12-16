package com.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/11 19:25
 */
@Getter
@Setter
@Entity(name = "oauth_user")
@Table(appliesTo = "oauth_user", comment = "用户表")
public class User extends GmtEntity implements UserDetails {

    @Column(nullable = false, unique = true, columnDefinition = "varchar(50) COMMENT '用户名'")
    private String username;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(50) COMMENT '手机号码'")
    private String phone;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(50) COMMENT '邮箱号码'")
    private String email;

    @Column(columnDefinition = "varchar(100) COMMENT '密码'")
    private String password;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '账户是否过期'")
    private boolean accountNonExpired = true;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '账户是否锁定'")
    private boolean accountNonLocked = true;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '凭证是否过期'")
    private boolean credentialsNonExpired = true;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT '1' COMMENT '账户是否有效'")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "oauth_user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorityList;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

}
