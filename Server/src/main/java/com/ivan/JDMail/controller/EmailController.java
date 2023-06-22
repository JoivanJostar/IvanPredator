package com.ivan.JDMail.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailController {
    private static final String USER = ""; //发送端的qq邮箱
    private static final String PASSWORD = ""; // 发送端的qq邮箱授权码
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");

    @GetMapping("/send")
    public String sendEmail(){

        Date date = new Date();
        String nowTime = simpleDateFormat.format(date);
        String to=""; //接收端的邮箱
        String title=nowTime+"已抢到一个火神！赶快去支付！";
        String text="<h1> hello world!<//h1>";
        if(sendMail(to,text,title)){
            System.out.println(nowTime+"发送一个邮件到"+to);
        }
        return "OK";
    }
    @GetMapping("/login")
    public String login(){

        Date date = new Date();
        String nowTime = simpleDateFormat.format(date);
        String to="3289747514@qq.com";
        String title=nowTime+"登录已失效";
        String text="<h1> hello world!<//h1>";
        if(sendMail(to,text,title)){
            System.out.println(nowTime+"发送重新登录邮件到"+to);
        }
        return "OK";
    }
    @GetMapping("/plus")
    public String plus(){
        Date date = new Date();
        String nowTime = simpleDateFormat.format(date);
        System.out.println(nowTime+"放货一次");
        return "OK";
    }
    /**
     * 发邮件
     * @param to 收件人地址
     * @param text 正文 Body
     * @param title 邮件标题
     * @return
     */
    public static boolean sendMail(String to, String text, String title){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}

