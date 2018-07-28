package com.fuzamei.common.model.dto;

import lombok.Data;

/**
 * @program: bonus-point-cloud
 * @description: 分页类，用于其他类继承
 * @author: WangJie
 * @create: 2018-06-28 08:56
 **/
@Data
public class PageDTO {
    /** 模糊匹配信息*/
    private String fuzzyMatch;
    /** 分页大小*/
    private Integer pageSize;
    /** 当前页数*/
    private Integer page;

    /** 起始时间 */
    private Long startTime;
    /** 结束时间 */
    private Long endTime;

    /**排序类型 1：由大到小，2：由小到大 */
    private Integer orderType;
}
