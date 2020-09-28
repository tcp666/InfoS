package com.keshe.tools;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    public static void sendEmail(String sendTo,String yzm) throws Exception {
        HtmlEmail email = new HtmlEmail();
        //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setHostName("smtp.163.com");
        //设置发送的字符类型
        email.setCharset("utf-8");
        //设置收件人
        email.addTo(sendTo);
        //发送人的邮箱为自己的，用户名可以随便填
        email.setFrom("gyhdxwang@163.com","wf");
        //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setAuthentication("gyhdxwang@163.com","gx19960718");
        //设置发送主题
        email.setSubject("验证码");
        //设置发送内容
        email.setMsg("您的注册验证码为："+yzm);
        //进行发送
        email.send();

    }

    public static String YZMUtil(){
        String yzm = "";
        for (int i = 0 ; i < 4 ; i ++) {
            yzm = yzm + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
        }
        return yzm;
    }
}
