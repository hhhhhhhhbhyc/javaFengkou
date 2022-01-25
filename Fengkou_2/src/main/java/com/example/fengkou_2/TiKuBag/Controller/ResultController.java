package com.example.fengkou_2.TiKuBag.Controller;

import com.example.fengkou_2.PersonalBag.Mapper.PersonalAccuracyMapper;
import com.example.fengkou_2.TiKuBag.Mapper.TiKuMapper;
import com.example.fengkou_2.TiKuBag.Mapper.TiaozhanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import javax.servlet.http.HttpSession;


@Controller
public class ResultController {
    /**
     * 获取时间差
     * @param endTime
     * @param startTime
     * @return
     */
    public int getTimeDifference(Timestamp endTime, Timestamp startTime) {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        long t1 = endTime.getTime();
        long t2 = startTime.getTime();
        int hours=(int) ((t1 - t2)/(1000*60*60));
        int minutes=(int) (((t1 - t2)/1000-hours*(60*60))/60);
        int second=(int) ((t1 - t2)/1000-hours*(60*60)-minutes*60);
        return hours*3600+minutes*60+second;
    }


    @Autowired
    private TiKuMapper tiKuMapper;
    @Autowired
    private TiaozhanMapper tiaozhanMapper;
    @Autowired
    private PersonalAccuracyMapper personalAccuracyMapper;

    /**
     * 通用答案判断函数
     * @param ans
     * @param realans
     * @param tikuid
     * @param attr
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/getResult")
    public String dealResult(@RequestParam("ans")String ans,
                             @RequestParam("realans")String realans,
                             @RequestParam("tikuid")String tikuid,
                             @RequestParam("oritime") String oritime,
                             RedirectAttributes attr,
                             Model model,
                             HttpSession session){

        String username = session.getAttribute("username").toString();
        Date date=new Date();
        Timestamp sqlDate = new Timestamp(date.getTime());
        Timestamp sqloritime = Timestamp.valueOf(oritime);
        int useTime = getTimeDifference(sqlDate, sqloritime);//已使用时间
        //System.out.println(useTime);

        if (ans.equals(realans)){
            tiKuMapper.insert(username,tikuid,1,sqlDate,useTime);
            attr.addFlashAttribute("message","恭喜您，答对了");
            return "redirect:/singletimu/"+tikuid;
        }
        else {
            tiKuMapper.insert(username,tikuid,0,sqlDate,useTime);
            attr.addFlashAttribute("message","请再接再厉");
            attr.addFlashAttribute("realans","正确答案是："+realans);
            return "redirect:/singletimu/"+tikuid;
        }
    }

    /**
     * 专用于考试判断正确函数
     * @param ans
     * @param realans
     * @param tiaozhanid
     * @param charp
     * @param tick
     * @param attr
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/getResultS")
    public String dealResult2(@RequestParam("ans")String ans,
                             @RequestParam("realans")String realans,
                             @RequestParam("tiaozhanid")int tiaozhanid,
                              @RequestParam("charp")int charp,
                              @RequestParam("tick")int tick,
                             RedirectAttributes attr,
                             Model model,
                             HttpSession session){

        if (tick!=3905) {//没有到规定的5题
            if (ans.equals(realans)) {
                //正确了，继续答题
                return "redirect:/tiaozhan/" + charp+"/"+tick+"/"+tiaozhanid;
            } else {
                //出现一次错误，假设检验失败，认为考核不及格
                attr.addFlashAttribute("result","很遗憾，您未能完成挑战");
                return "redirect:/personaltiaozhan";
            }
        }else {
            Date date=new Date();
            Timestamp sqlDate_ori = new Timestamp(date.getTime());

            //5题均正确，获得成就
            attr.addFlashAttribute("result","恭喜您，您完成了此次挑战");
            String username = session.getAttribute("username").toString();
            tiaozhanMapper.insertTiaozhan(username,tiaozhanid,1);
            personalAccuracyMapper.insertletter("挑战达成","恭喜您，完成了第"+charp+"章挑战",sqlDate_ori,username);
            return "redirect:/personaltiaozhan";
        }
    }
}
