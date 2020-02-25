package com.ujiuye.crmpro.common.utils;

import com.ujiuye.crmpro.employee.pojo.Email;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailUtils {
    public static void send(Email email){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(25);//默认端口，标准的SMTP端口
        mailSender.setUsername("1546562095@qq.com");//用户名
        mailSender.setPassword("xgvvrezwufhiihhd");//密码/授权码


        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(msg, true,"utf-8");
            helper.setFrom(mailSender.getUsername());
            helper.setTo(email.getReceiver());//收件人邮箱
            helper.setSubject(email.getTitle());//主题
            helper.setText(email.getContent(),true);//内容  网页版
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(msg);
        System.out.println("邮件发送完毕");

    }
}
