package com.qbanxiaoli.sms.dao.querydsl;

import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.Template;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/16 10:29
 */
public interface SmsDao {

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return Template 短信模版
     * @author qbanxiaoli
     * @description 查询短信模版
     */
    Template findSmsTemplate(SmsFormDTO smsFormDTO);

}
