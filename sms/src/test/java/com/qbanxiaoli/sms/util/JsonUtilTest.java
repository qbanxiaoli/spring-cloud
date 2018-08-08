package com.qbanxiaoli.sms.util;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.qbanxiaoli.common.util.JsonUtil;
import com.qbanxiaoli.common.util.SendSmsUtil;
import com.qbanxiaoli.sms.model.vo.SendSmsResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/8/9 上午4:02
 */
@Slf4j
public class JsonUtilTest {

    @Test
    public void Test() throws Exception {
        SendSmsResponse sendSmsResponse = SendSmsUtil.sendSms("15957180964", "123456");
        String json = JsonUtil.toJsonString(sendSmsResponse);
        log.info(json);
        SendSmsResponseVO sendSmsResponseVO = JsonUtil.toObject(json, SendSmsResponseVO.class);
        log.info(JsonUtil.toJsonString(sendSmsResponseVO));
    }

}
