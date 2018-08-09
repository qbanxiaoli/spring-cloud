package com.qbanxiaoli.sms.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.RandomUtil;
import com.qbanxiaoli.common.util.SendSmsUtil;
import com.qbanxiaoli.sms.dao.SmsDao;
import com.qbanxiaoli.sms.enums.SmsResponseEnum;
import com.qbanxiaoli.sms.model.converter.MessageAssembly;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.Message;
import com.qbanxiaoli.sms.model.vo.SendSmsResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Optional;

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
     * @param smsFormDTO 短信请求数据传输类
     * @return 请求响应
     * @author qbanxiaoli
     * @description 获取短信验证码
     */
    public ResponseVO<SendSmsResponseVO> sendMessage(SmsFormDTO smsFormDTO) {
        //获取6位数随机验证码
        String captcha = RandomUtil.generateString(6);
        //新建返回对象
        SendSmsResponseVO sendSmsResponseVO = new SendSmsResponseVO();
        //发送短信验证码
        try {
            log.info("向手机号" + smsFormDTO.getPhone() + "发送了一条短信验证码为：" + captcha);
            SendSmsResponse sendSmsResponse = SendSmsUtil.sendSms(smsFormDTO.getPhone(), captcha);
            //属性拷贝
            BeanUtils.copyProperties(sendSmsResponse, sendSmsResponseVO);
        } catch (ClientException e) {
            log.error("短信验证码发送失败：" + e);
            return new ResponseVO<>(SmsResponseEnum.MSG_SEND_FAILURE);
        }
        //返回数据
        if (sendSmsResponseVO.getCode() == null || !sendSmsResponseVO.getCode().equals("OK")) {
            log.error("短信验证码发送失败");
            return new ResponseVO<>(SmsResponseEnum.MSG_SEND_FAILURE, sendSmsResponseVO);
        }
        //装配短信实体类
        Message message = MessageAssembly.toDomain(sendSmsResponseVO, smsFormDTO, captcha);
        //保存发送短信
        try {
            smsDao.save(message);
        } catch (Exception e) {
            log.error("短信保存失败：" + e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseVO<>(SmsResponseEnum.MSG_SAVE_FAILURE);

        }
        log.info("短信验证码发送成功");
        return new ResponseVO<>(SmsResponseEnum.MSG_SEND_SUCCESS, sendSmsResponseVO);
    }

}
