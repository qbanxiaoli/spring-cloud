package com.qbanxiaoli.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/14 13:27
 */
@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    TCM_ADMIN("TCM_ADMIN", "管理员"),
    TCM_COMPANY("TCM_COMPANY", "公司"),
    TCM_RESTAURANT("TCM_RESTAURANT", "餐厅"),
    TCM_EMPLOYEE("TCM_EMPLOYEE", "员工"),
    TCM_USER("TCM_USER", "普通用户");

    private String authority;

    private String description;

}
