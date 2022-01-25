package com.example.fengkou_2.LuntanBag.Controller;

import com.example.fengkou_2.LuntanBag.Mapper.LuntanMapper;
import com.example.fengkou_2.LuntanBag.Model.LuntanModel;
import com.example.fengkou_2.PersonalBag.Mapper.PersonalAccuracyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class LuntanController {
    @Autowired
    private LuntanMapper luntanMapper;
    @Autowired
    private PersonalAccuracyMapper personalAccuracyMapper;
    /**
     * 读取论坛内容
     */
    @GetMapping("/luntan/{tid}")
    public String getLuntan(@PathVariable("tid") int tid,
                            RedirectAttributes attr,
                            Model model){

        List<LuntanModel>  luntan = luntanMapper.selectallfromluntan(tid);

        attr.addFlashAttribute("luntan",luntan);
        return "redirect:/singletimu/"+tid;
    }

    @PostMapping("/luntan/{tid}")
    public String postLuntan(@PathVariable("tid") int tid,
                             @RequestParam("somecontext")String somecontext,
                             HttpSession session
                             ){
        String username = session.getAttribute("username").toString();
        Date date=new Date();
        Timestamp sqlDate = new Timestamp(date.getTime());

        luntanMapper.insert(tid,username,somecontext,sqlDate);
        return "redirect:/singletimu/"+tid;
    }

    /**
     * 点赞
     */
    @GetMapping("/addlikes/{luntanid}/{tid}")
    public String likes(@PathVariable("tid") int tid,
                        @PathVariable("luntanid") int lid,
                            RedirectAttributes attr,
                            Model model,
                        HttpSession session){
        String username = session.getAttribute("username").toString();
        luntanMapper.addLikes(lid);
        String auther = luntanMapper.selectAuther(lid);
        Date date=new Date();
        Timestamp sqlDate_ori = new Timestamp(date.getTime());
        personalAccuracyMapper.insertletter("点赞信息","用户 "+username+"对您在编号为"+tid+"的问题的评论点了一个赞",sqlDate_ori,auther);
        return "redirect:/singletimu/"+tid;
    }


}
