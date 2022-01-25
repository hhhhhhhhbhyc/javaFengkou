package com.example.fengkou_2.LoginRegoster.Controller;

import com.example.fengkou_2.LoginRegoster.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class ForgetpasswordController {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/forgetpassword")
    public String getLogin(){
        return "forgetpassword";
    }


    @PostMapping("/forgetpassword1")
    public String getLogin(@RequestParam("username")String username,
                           @RequestParam("email")String email,
                           @RequestParam("newpassword")String newpassword,
                           Model model){


        String email_2 = userMapper.selectemailforloginJudje(username);

        if (email.equals(email_2)){
            //MD5
            String encodekey = DigestUtils.md5DigestAsHex(newpassword.getBytes());
            userMapper.updateMiusPro(encodekey,username);
            //邮件
            System.out.println("x");
            Date date=new Date();
            Timestamp sqlDate = new Timestamp(date.getTime());

            SimpleMailMessage mailMessage=new SimpleMailMessage();

            mailMessage.setSubject("账号密码改变");
            String text = "于 "+sqlDate+" 您的账号密码已被改变。如不是您个人操作，请尽快联系系统管理员";

            mailMessage.setText(text);

            mailMessage.setTo(email);
            mailMessage.setFrom("1658460876@qq.com");

            mailSender.send(mailMessage);

            return "redirect:/";
        }else{
            model.addAttribute("message","个人信息错误，请重新验证");
            return "forgetpassword";
        }

    }
}
