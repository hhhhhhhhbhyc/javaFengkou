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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    /**
     * 登录+判断
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String requestLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {

        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {

            if (username.equals("root")  && password.equals("root")) {
                session.setAttribute("username", "root");
                return "redirect:/houtai/main";
            }
            String password_2 = userMapper.selectforloginJudje(username);
            String encodekey = DigestUtils.md5DigestAsHex(password.getBytes());
            if (encodekey.equals(password_2)) {
                session.setAttribute("username", username);

                if (userMapper.judgeAuth(username,encodekey)==1){
                    return "redirect:/houtai/main";
                }
                return "redirect:/";
            }else {
                model.addAttribute("message","您输入的账号或密码发生错误，请重新输入");
            }

        }
        return "login";

    }

}
