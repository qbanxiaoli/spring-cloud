package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/19 9:01 PM
 */
@Getter
@Setter
@Entity(name = "fastdfs_file")
@Table(appliesTo = "fastdfs_file", comment = "分布式文件存储表")
@ApiModel(value = "文件存储模型")
public class FastDFSFile extends GmtEntity {

    @Column(nullable = false, columnDefinition = "varchar(30) COMMENT '文件服务器访问地址'")
    @ApiModelProperty(value = "文件服务器访问地址", required = true)
    private String webServerUrl;

    @Column(nullable = false, columnDefinition = "varchar(60) COMMENT '文件路径'")
    @ApiModelProperty(value = "文件路径", required = true)
    private String storePath;

    @Column(nullable = false, columnDefinition = "varchar(20) COMMENT '文件类型'")
    @ApiModelProperty(value = "文件类型", required = true)
    private String contentType;

    @Column(nullable = false, columnDefinition = "varchar(10) COMMENT '文件扩展名'")
    @ApiModelProperty(value = "文件扩展名", required = true)
    private String fileExtension;

    @Column(nullable = false, columnDefinition = "bigint COMMENT '文件大小'")
    @ApiModelProperty(value = "文件大小", required = true)
    private Long fileSize;

}

