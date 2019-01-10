package com.i18n;

import com.enums.response.CommonResponseEnum;
import com.model.vo.ResponseVO;
import com.util.JsonUtil;
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
    public void Test() throws Exception {
        ResponseVO responseVO = new ResponseVO<>(CommonResponseEnum.SUCCESS_VARIABLE, new Object[]{LocaleContextHolder.getLocale()});
        log.info(JsonUtil.toJsonString(responseVO));
    }
}
