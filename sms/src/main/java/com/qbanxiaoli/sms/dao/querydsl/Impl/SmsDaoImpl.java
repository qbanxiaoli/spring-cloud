package com.qbanxiaoli.sms.dao.querydsl.Impl;

import com.qbanxiaoli.sms.dao.querydsl.SmsDao;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.QInformation;
import com.qbanxiaoli.sms.model.entity.QTemplate;
import com.qbanxiaoli.sms.model.entity.Template;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/16 10:33
 */
@Component
public class SmsDaoImpl implements SmsDao {

    private JPAQueryFactory queryFactory;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public SmsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return Template 短信模版
     * @author qbanxiaoli
     * @description 查询短信模版
     */
    @Override
    public Template findSmsTemplate(SmsFormDTO smsFormDTO) {
        //添加查询条件
        Predicate predicate1 = QInformation.information.userId.eq(smsFormDTO.getUserId());
        Predicate predicate2 = QInformation.information.projectName.eq(smsFormDTO.getProjectName());
        Predicate predicate3 = QTemplate.template.type.eq(smsFormDTO.getType());
        //查询短信模板
        JPAQuery<Template> jpaQuery = queryFactory.select(QTemplate.template)
                .from(QInformation.information)
                .innerJoin(QTemplate.template)
                .on(QInformation.information.id.intValue().eq(QTemplate.template.informationId.intValue()))
                .where(predicate1, predicate2, predicate3);
        //拿到结果
        return jpaQuery.fetchOne();
    }

}
