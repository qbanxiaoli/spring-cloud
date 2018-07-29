package com.qbanxiaoli.sms.dao;

import com.qbanxiaoli.common.dao.BaseDao;
import com.qbanxiaoli.sms.model.entity.Message;
import org.springframework.stereotype.Repository;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:14
 */
@Repository
public interface SmsDao extends BaseDao<Message, Long> {

}
