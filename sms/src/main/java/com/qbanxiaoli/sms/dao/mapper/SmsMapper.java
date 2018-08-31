package com.qbanxiaoli.sms.dao.mapper;

import com.qbanxiaoli.common.dao.mapper.TkMapper;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.Sms;
import com.qbanxiaoli.sms.model.entity.Template;
import com.qbanxiaoli.sms.sql.SmsSqlFactory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/11 10:15
 */
@Repository
public interface SmsMapper extends TkMapper<Sms> {

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return Template 短信模版
     * @author qbanxiaoli
     * @description 查询短信模版
     */
    @SelectProvider(type = SmsSqlFactory.class, method = "findSmsTemplate")
    Template findSmsTemplate(SmsFormDTO smsFormDTO);

}
