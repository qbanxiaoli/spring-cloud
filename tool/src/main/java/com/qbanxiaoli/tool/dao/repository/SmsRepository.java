package com.qbanxiaoli.tool.dao.repository;

import com.qbanxiaoli.common.dao.repository.BaseRepository;
import com.qbanxiaoli.tool.dao.querydsl.SmsDao;
import com.qbanxiaoli.tool.model.entity.Sms;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:14
 */
public interface SmsRepository extends SmsDao, BaseRepository<Sms, Long> {

}
