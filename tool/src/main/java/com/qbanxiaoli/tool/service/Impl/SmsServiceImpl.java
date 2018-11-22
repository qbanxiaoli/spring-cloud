package com.qbanxiaoli.tool.service.Impl;

import com.qbanxiaoli.common.model.vo.ResponseVO;
import com.qbanxiaoli.common.util.RandomUtil;
import com.qbanxiaoli.tool.dao.repository.SmsRepository;
import com.qbanxiaoli.tool.dao.repository.TemplateRepository;
import com.qbanxiaoli.tool.enums.SmsResponseEnum;
import com.qbanxiaoli.tool.model.converter.SmsAssembly;
import com.qbanxiaoli.tool.model.dto.SmsFormDTO;
import com.qbanxiaoli.tool.model.entity.Sms;
import com.qbanxiaoli.tool.model.entity.Template;
import com.qbanxiaoli.tool.model.vo.SendSmsResponseVO;
import com.qbanxiaoli.tool.service.SmsService;
import com.qbanxiaoli.tool.util.SendSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 1:24
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    private final SmsRepository smsRepository;

    private final TemplateRepository templateRepository;

    @Autowired
    public SmsServiceImpl(SmsRepository smsRepository, TemplateRepository templateRepository) {
        this.smsRepository = smsRepository;
        this.templateRepository = templateRepository;
    }

    /**
     * @param smsFormDTO 短信请求数据传输类
     * @return 请求响应
     * @author qbanxiaoli
     * @description 获取短信验证码
     */
    @Override
    public ResponseVO<SendSmsResponseVO> sendSms(SmsFormDTO smsFormDTO) {
        Template template = templateRepository.findByType(smsFormDTO.getType());
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
        //保存发送短信记录
        try {
            smsRepository.save(sms);
        } catch (Exception e) {
            log.error("短信保存失败：" + e);
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
