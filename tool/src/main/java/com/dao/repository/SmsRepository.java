package com.dao.repository;

import com.model.entity.Sms;
import com.dao.querydsl.SmsDao;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:14
 */
public interface SmsRepository extends SmsDao, BaseRepository<Sms, Long> {

}
