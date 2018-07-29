package com.qbanxiaoli.sms.model.entity;

import com.qbanxiaoli.common.model.entity.GmtEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message extends GmtEntity {

    /** 用户手机号 */
    @Column(nullable = false, length = 20)
    private String phone;

    /** 业务类型 */
    @Column(nullable = false, length = 2)
    private String type;

    /** 验证码 */
    @Column(nullable = false, length = 10)
    private String code;

}
