package com.keshe.tools;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@Component
public class Verify {

    private static Properties properties = new Properties();

    public static Random random = new Random();

    static {
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
    }



    public String sendMsg(String email, String checkcode) throws MessagingException {
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("578499233@qq.com"));
        // 设置收件人邮箱地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 设置邮件标题
        message.setSubject("infoS");

        // 生成4位数字验证码
        StringBuilder sb = new StringBuilder(checkcode);


        // 设置邮件内容
        message.setText("验证码为：" + sb.toString());
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("578499233@qq.com", "oijohftqyzmpbchf");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return sb.toString();
    }

    public String getchcekcode(){
        String source = "1234567890";
        StringBuffer check = new StringBuffer();
        for (int i = 0; i < 4;i++) {
            int x = ((int) (Math.random() * 100)) % 10;
            if (x == 9)
                x = x - 1;
            check.append(source.substring(x, x + 1));
            System.out.println(source.substring(x, x + 1));
        }
        return check.toString();
    }


}
