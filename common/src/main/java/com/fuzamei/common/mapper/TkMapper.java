package com.fuzamei.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/7/3 13:22
 */
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
//特别注意，该接口不能被扫描到，否则会出错
}
