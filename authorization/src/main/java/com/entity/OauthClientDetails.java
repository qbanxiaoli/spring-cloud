package com.entity;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/11 20:37
 */
@Data
@Entity
@Table(appliesTo = "oauth_client_details", comment = "客户端详情存储表")
public class OauthClientDetails {

    @Id
    @Column(columnDefinition = "varchar(256) COMMENT '指定客户端标识'")
    private String clientId;

    @Column(columnDefinition = "varchar(256) COMMENT '指定客户端所能访问的资源id集合'")
    private String resourceIds;

    @Column(columnDefinition = "varchar(256) COMMENT '指定客户端的访问密匙'")
    private String clientSecret;

    @Column(columnDefinition = "varchar(256) COMMENT '指定客户端申请的权限范围'")
    private String scope;

    @Column(columnDefinition = "varchar(256) COMMENT '指定客户端支持的授权模式'")
    private String authorizedGrantTypes;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端的重定向URI,可为空'")
    private String webServerRedirectUri;

    @Column(columnDefinition = "varchar(256) COMMENT '指定客户端所拥有的Spring Security的权限值'")
    private String authorities;

    @Column(columnDefinition = "int COMMENT '设定客户端的access_token的有效时间值(单位:秒),可选'")
    private String accessTokenValidity;

    @Column(columnDefinition = "int COMMENT '设定客户端的refresh_token的有效时间值(单位:秒),可选'")
    private String refreshTokenValidity;

    @Column(columnDefinition = "varchar(4096) COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选'")
    private String additionalInformation;

    @Column(columnDefinition = "varchar(256) COMMENT '设置用户是否自动Approval操作, 默认值为 'false''")
    private String autoapprove;

}
