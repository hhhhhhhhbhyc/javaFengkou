package com.example.fengkou_2.LoginRegoster.Controller;

import com.example.fengkou_2.LoginRegoster.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取注册页面
     * @return
     */
    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    /**
     * 注册账号+哈希
     * @param username
     * @param password
     * @param email
     * @param session
     * @return
     */
    @PostMapping("/register")
    public String requestRegister(@RequestParam("username")String username,
                                  @RequestParam("password")String password,
                                  @RequestParam("passwordre")String passwordre,
                                  @RequestParam("email")String email,
                                  HttpSession session,
                                  Model model){
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            if (password.equals(passwordre)) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                String encodekey = DigestUtils.md5DigestAsHex(password.getBytes());
                try {
                    userMapper.insert(username, encodekey, email);
                }catch (Exception e){
                    model.addAttribute("message","您的用户名已被注册，请换一个后重新输入");
                    return "register";
                }
                return "redirect:/";
            }else {
                model.addAttribute("message","您2次密码输入不一致，请重新输入");
            }
        }else{
            model.addAttribute("message","您有必填的信息尚未填写，请重新输入");
        }
        return "register";
    }
}
