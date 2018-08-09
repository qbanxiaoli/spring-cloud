package com.qbanxiaoli.sms.i18n;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.JsonUtil;
import com.qbanxiaoli.sms.enums.SmsResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author qbanxiaoli
 * @description
 * @create 2018/5/9 19:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class I18nTest {

    //返回值传入消息变量测试
    @Test
    public void Test() {
        ResponseVO responseVO = new ResponseVO<>(SmsResponseEnum.SUCCESS_VARIABLE, new Object[]{LocaleContextHolder.getLocale()});
        log.info(JsonUtil.toJsonString(responseVO));
    }
}
