package com.qbanxiaoli.sms.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.SendSmsUtil;
import com.qbanxiaoli.sms.dao.SmsDao;
import com.qbanxiaoli.sms.enums.SmsResponseEnum;
import com.qbanxiaoli.sms.model.converter.MessageAssembly;
import com.qbanxiaoli.sms.model.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:24
 */
@Slf4j
@Service
@Transactional
public class SmsService {

    private final SmsDao smsDao;

    @Autowired
    public SmsService(SmsDao smsDao) {
        this.smsDao = smsDao;
    }

    /**
     * @param phone 手机号码
     * @return 请求响应
     * @author qbanxiaoli
     * @description 获取短信验证码
     */
    public ResponseVO sendMessage(String phone) {
        //获取6位数验证码
        String captcha = SendSmsUtil.getRandNum(1, 999999);
        //发送短信验证码
        SendSmsResponse sendSmsResponse;
        try {
            log.info("向手机号" + phone + "发送了一条短信验证码为：" + captcha);
            sendSmsResponse = SendSmsUtil.sendSms(phone, captcha);
        } catch (ClientException e) {
            log.info("短信验证码发送失败");
            return new ResponseVO<>(SmsResponseEnum.MSG_SEND_FAILURE);
        }
        //装配短信实体类
        Message message = MessageAssembly.toDomain(sendSmsResponse, phone, captcha);
        //保存发送短信
        try {
            smsDao.save(message);
        } catch (Exception e) {
            log.info("短信保存失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseVO<>(SmsResponseEnum.MSG_SAVE_FAILURE);

        }
        //返回数据
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            log.info("短信验证码发送成功");
            return new ResponseVO<>(SmsResponseEnum.MSG_SEND_SUCCESS, sendSmsResponse);
        }
        log.info("短信验证码发送失败");
        return new ResponseVO<>(SmsResponseEnum.MSG_SEND_FAILURE, sendSmsResponse);
    }

}
