package com.qbanxiaoli.sms.dao.dao;

import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.Template;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/16 10:29
 */
public interface SmsDao {

    Template findSmsTemplate(SmsFormDTO smsFormDTO);

}
