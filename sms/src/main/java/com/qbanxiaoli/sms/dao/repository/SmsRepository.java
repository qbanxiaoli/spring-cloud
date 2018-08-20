package com.qbanxiaoli.sms.dao.repository;

import com.qbanxiaoli.common.dao.repository.BaseRepository;
import com.qbanxiaoli.sms.dao.dao.SmsDao;
import com.qbanxiaoli.sms.model.entity.Sms;
import com.qbanxiaoli.sms.model.entity.Template;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:14
 */
public interface SmsRepository extends SmsDao, BaseRepository<Sms, Long> {

}