package com.qbanxiaoli.sms.service.Impl;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.RandomUtil;
import com.qbanxiaoli.sms.dao.mapper.SmsMapper;
import com.qbanxiaoli.sms.dao.repository.SmsRepository;
import com.qbanxiaoli.sms.enums.SmsResponseEnum;
import com.qbanxiaoli.sms.model.converter.SmsAssembly;
import com.qbanxiaoli.sms.model.dto.SmsFormDTO;
import com.qbanxiaoli.sms.model.entity.Sms;
import com.qbanxiaoli.sms.model.entity.Template;
import com.qbanxiaoli.sms.model.vo.SendSmsResponseVO;
import com.qbanxiaoli.sms.service.SmsService;
import com.qbanxiaoli.sms.util.SendSmsUtil;
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
public class SmsServiceImpl implements SmsService {

    private final SmsRepository smsRepository;

    private final SmsMapper smsMapper;

    @Autowired
    public SmsServiceImpl(SmsRepository smsRepository, SmsMapper smsMapper) {
        this.smsRepository = smsRepository;
        this.smsMapper = smsMapper;
    }

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return 请求响应
     * @author qbanxiaoli
     * @description 获取短信验证码
     */
    @Override
    public ResponseVO<SendSmsResponseVO> sendSms(SmsFormDTO smsFormDTO) {
        Template template = smsRepository.findSmsTemplate(smsFormDTO);
//        Template template = smsMapper.findSmsTemplateBySqlFactory(smsFormDTO);
        if (template == null) {
            log.warn("未找到对应短信模板");
            return new ResponseVO<>(SmsResponseEnum.TEMPLATE_NOT_FOUND);
        }
        //获取6位数随机验证码
        String captcha = RandomUtil.generateString(6);
        SendSmsResponseVO sendSmsResponseVO;
        //发送短信验证码
        try {
            log.info("向手机号" + smsFormDTO.getPhone() + "发送了一条短信验证码为：" + captcha);
            sendSmsResponseVO = SendSmsUtil.sendSms(template, smsFormDTO.getPhone(), captcha);
        } catch (Exception e) {
            log.error("短信验证码发送失败：" + e);
            return new ResponseVO<>(SmsResponseEnum.MSG_SEND_FAILURE);
        }
        //装配短信实体类
        Sms sms = SmsAssembly.toDomain(sendSmsResponseVO, smsFormDTO, captcha);
        //保存发送短信
        try {
            smsRepository.save(sms);
//            smsMapper.insert(sms);
        } catch (Exception e) {
            log.error("短信保存失败：" + e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseVO<>(SmsResponseEnum.MSG_SAVE_FAILURE);
        }
        //返回数据
        if (sendSmsResponseVO.getCode() != null && sendSmsResponseVO.getCode().equals("OK")) {
            log.info("短信验证码发送成功");
            return new ResponseVO<>(SmsResponseEnum.MSG_SEND_SUCCESS, sendSmsResponseVO);
        }
        log.error("短信验证码发送失败");
        return new ResponseVO<>(SmsResponseEnum.MSG_SEND_FAILURE, sendSmsResponseVO);
    }

}
