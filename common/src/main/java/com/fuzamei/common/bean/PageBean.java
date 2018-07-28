package com.fuzamei.common.bean;

import lombok.Data;
import java.util.List;

/**
 * @author qbanxiaoli
 * @description 自定义分页器
 * @create 2018/4/19
 */
@Data
public class PageBean<T> {
    /** 当前页 */
    private Integer currentPage;
    /** 每页显示的总条数 */
    private Integer pageSize;
    /** 总条数 */
    private Integer totalNum;
    /** 是否有下一页 */
    private Integer isMore;
    /** 总页数 */
    private Integer totalPage;
    /** 开始索引 */
    private Integer startIndex;
    /** 分页结果 */
    private List<T> obj;

    public PageBean(List<T> obj, Integer currentPage, Integer pageSize, Integer totalNum) {
        this.obj = obj;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        if (pageSize > 0) {
            this.totalPage = (this.totalNum + this.pageSize - 1) / this.pageSize;
        } else {
            this.totalPage = 1;
        }
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        this.isMore = this.currentPage >= this.totalPage ? 0 : 1;
    }

}