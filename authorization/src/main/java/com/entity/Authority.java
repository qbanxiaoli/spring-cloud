package com.entity;

import com.qbanxiaoli.common.model.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/12 15:40
 */
@Getter
@Setter
@Entity(name = "oauth_authority")
@Table(appliesTo = "oauth_authority", comment = "权限表")
public class Authority extends IdEntity implements GrantedAuthority {

    @Column(nullable = false, unique = true, columnDefinition = "varchar(50) COMMENT '用户权限'")
    private String authority;

    @ManyToMany(mappedBy = "authorityList")
    private List<User> userList;

}
