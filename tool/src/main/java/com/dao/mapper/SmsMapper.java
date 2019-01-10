package com.dao.mapper;

import com.model.dto.SmsFormDTO;
import com.model.entity.Sms;
import com.model.entity.Template;
import com.sql.SmsSqlFactory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/11 10:15
 */
@Mapper
@Repository
public interface SmsMapper extends TkMapper<Sms> {

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return Template 短信模版
     * @author qbanxiaoli
     * @description 查询短信模版
     */
    //方式一：通过反射的方式映射到java类中的方法
    @SelectProvider(type = SmsSqlFactory.class, method = "findSmsTemplateBySqlFactory")
    Template findSmsTemplateBySqlFactory(SmsFormDTO smsFormDTO);

    //方式二：通过xml与接口进行绑定
    Template findSmsTemplateByXML(@Param("smsFormDTO") SmsFormDTO smsFormDTO);

}
