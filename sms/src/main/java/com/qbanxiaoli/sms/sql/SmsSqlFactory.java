package com.qbanxiaoli.sms.sql;

import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/8/30 下午4:02
 */
@Slf4j
public class SmsSqlFactory {

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return SQL语句
     * @author qbanxiaoli
     * @description 查询短信模版SQL
     */
    public String findSmsTemplate(SmsFormDTO smsFormDTO) {
        String sql = new SQL() {{
            SELECT("*");
            FROM("information i");
            INNER_JOIN("template t ON i.id = t.information_id");
            WHERE("i.user_id = " + smsFormDTO.getUserId());
            WHERE("i.project_name = " + "'" + smsFormDTO.getProjectName() + "'");
            WHERE("t.type = " + smsFormDTO.getType());
        }}.toString();
        log.info("查询短信模版SQL:\n" + sql);
        return sql;
    }

}
