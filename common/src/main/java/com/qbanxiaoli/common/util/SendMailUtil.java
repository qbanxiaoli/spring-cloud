package com.qbanxiaoli.common.util;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Slf4j
public class SendMailUtil {

    private SendMailUtil() {
        throw new AssertionError("不能实例化SendMailUtil");
    }

    private static final String HOST = "smtp.qq.com";
    private static final int PORT = 465;
    private static final String SENDER = "823730820@qq.com";//此处需要与host相同的网站
    private static final String SENDERPWD = "cmlzlhpqjhifbffa";

    /**
     * @return Session
     * @author qbanxiaoli
     * @description 获取用于发送邮件的Session
     */
    private static Session getSession() throws Exception {
        // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", true);
        //此处填写SMTP服务器
        props.put("mail.smtp.host", HOST);//设置服务器地址
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个465
        props.put("mail.smtp.port", PORT);//设置端口
        // 此处填写你的账号
        props.put("mail.user", SENDER);
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", SENDERPWD);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        //使用环境属性和授权信息，创建邮件会话
        return Session.getInstance(props, authenticator);
    }

    /**
     * @param receiver 邮件接收者
     * @param content  邮件内容
     * @author qbanxiaoli
     * @description 发送邮件
     */
    public static void sendMail(String receiver, String content) throws Exception {
        Session session = getSession();
        try {
            log.info("-------开始发送-------");
            Message msg = new MimeMessage(session);
            //设置message属性
            msg.setFrom(new InternetAddress(SENDER));
            InternetAddress[] address = {new InternetAddress(receiver)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("短信测试");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");
            //开始发送
            Transport.send(msg);
            log.info("-------发送完成-------");
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }
}
