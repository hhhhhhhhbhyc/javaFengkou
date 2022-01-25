package com.example.fengkou_2.MailBag.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {
    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 收集用户信息-邮件
     * @param name
     * @param email
     * @param subject
     * @param message
     */
    @PostMapping("/sendmail")
    public void SendMail(@RequestParam("name")String name,
                         @RequestParam("email")String email,
                         @RequestParam("subject")String subject,
                         @RequestParam("message")String message
                         ){
        SimpleMailMessage mailMessage=new SimpleMailMessage();

        mailMessage.setSubject(subject);

        String text = "建议者姓名: "+name+"\n"+"建议者邮箱: "+email+"建议者留言："+message;
        mailMessage.setText(text);
        mailMessage.setTo("1658460876@qq.com");
        mailMessage.setFrom("1658460876@qq.com");

        //System.out.println("s");
        mailSender.send(mailMessage);

    }
}
