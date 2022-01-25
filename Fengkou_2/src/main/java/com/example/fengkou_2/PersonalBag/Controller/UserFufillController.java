package com.example.fengkou_2.PersonalBag.Controller;

import com.example.fengkou_2.PersonalBag.Mapper.UserFufillMapper;
import com.example.fengkou_2.Util.XiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class UserFufillController {
    @Autowired
    private UserFufillMapper userFufillMapper;

    /**
     * 个人信息完善
     *
     * @param attr
     * @return
     */
    @GetMapping("/user")
    public String getuserfufill(RedirectAttributes attr) {
        attr.addFlashAttribute("change", 1);
        return "redirect:/personalcenter";
    }

    @PostMapping("/personalupdate")
    public String updatepersonal(HttpSession session,
                                 @RequestParam("realname") String realname,
                                 @RequestParam("workplace") String workplace,
                                 @RequestParam("address") String address,
                                 @RequestParam("sex") String sex,
                                 @RequestPart("pic") MultipartFile picture
    ) {
        String username = session.getAttribute("username").toString();

        String fileName = picture.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        String url = "http://r54clgx6u.hd-bkt.clouddn.com/" + fileName;
        try {
            XiniuUtil.upload2Qiniu2(picture.getBytes(), fileName);
            userFufillMapper.updateuser(realname, workplace, address, Integer.parseInt(sex), url, username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/personalcenter";
    }
}
