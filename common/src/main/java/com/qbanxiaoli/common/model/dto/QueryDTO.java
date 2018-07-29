package com.qbanxiaoli.common.model.dto;

import lombok.Data;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/3 13:54
 */
@Data
public class QueryDTO {

    /** 每页记录数 */
    private Integer pageSize;

    /** 当前页 */
    private Integer currentPage;

}
