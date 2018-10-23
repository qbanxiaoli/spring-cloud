package com.qbanxiaoli.tool.dao.repository;

import com.qbanxiaoli.common.dao.repository.BaseRepository;
import com.qbanxiaoli.tool.model.entity.Template;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/10/24 01:41
 */
public interface TemplateRepository extends BaseRepository<Template, Long> {

    /**
     * @param type 业务类型
     * @return Template 短信模版
     * @author qbanxiaoli
     * @description 查询短信模版
     */
    Template findTemplateByType(Integer type);

}
