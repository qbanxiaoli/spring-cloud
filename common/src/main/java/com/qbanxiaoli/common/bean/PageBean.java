package com.qbanxiaoli.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author qbanxiaoli
 * @description 自定义分页器
 * @create 2018/4/19
 */
@Data
@ApiModel(value = "分页模型")
public class PageBean<T> {

    @ApiModelProperty(value = "当前页", required = true)
    private Integer currentPage;

    @ApiModelProperty(value = "每页显示的总条数", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "总条数", required = true)
    private Integer totalNum;

    @ApiModelProperty(value = "是否有下一页", required = true)
    private Integer isMore;

    @ApiModelProperty(value = "总页数", required = true)
    private Integer totalPage;

    @ApiModelProperty(value = "开始索引", required = true)
    private Integer startIndex;

    @ApiModelProperty(value = "分页结果", required = true)
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