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
    @Column(nullable = false, columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String clientId;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String resourceIds;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String clientSecret;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String scope;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String authorizedGrantTypes;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String webServerRedirectUri;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String authorities;

    @Column(columnDefinition = "int COMMENT '客户端标识'")
    private String accessTokenValidity;

    @Column(columnDefinition = "int COMMENT '客户端标识'")
    private String refreshTokenValidity;

    @Column(columnDefinition = "varchar(4096) COMMENT '客户端标识'")
    private String additionalInformation;

    @Column(columnDefinition = "varchar(256) COMMENT '客户端标识'")
    private String autoapprove;

}
