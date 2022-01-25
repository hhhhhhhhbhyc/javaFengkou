package com.example.fengkou_2.TiKuBag.Controller;

import com.example.fengkou_2.PersonalBag.Mapper.PersonalAccuracyMapper;
import com.example.fengkou_2.TiKuBag.Mapper.TiKuMapper;
import com.example.fengkou_2.TiKuBag.Mapper.TiaozhanMapper;
import com.example.fengkou_2.TiKuBag.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class SingleTImuController {
    @Autowired
    private TiKuMapper tiKuMapper;
    @Autowired
    private TiaozhanMapper tiaozhanMapper;
    @Autowired
    private PersonalAccuracyMapper personalAccuracyMapper;

    /**
     * 承接挑战
     *
     * @param model
     * @param charp
     * @return
     */
    @RequestMapping("/tiaozhan/{charp}/{tick}/{id}")
    private String IntoTiaozhan(Model model,
                                @PathVariable("charp") int charp,
                                @PathVariable("id") int tiaozhanid,
                                @PathVariable("tick") int tick) {
        //计数值
        tick = tick * 5 + 5;

        Integer tid = tiaozhanMapper.shuffleTikuByZhang(charp);
        //找到一个题目

        //判断题型
        int panduan = tiKuMapper.judgeTixin(tid);
        //返回
        //选择题
        if (panduan == 1) {
            ChoiceModel timu = tiKuMapper.ChoiceselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 1);

            String[] tizhi = new String[4];
            tizhi[0] = timu.getA();
            tizhi[1] = timu.getB();
            tizhi[2] = timu.getC();
            tizhi[3] = timu.getD();

            List<Comparable> list = Arrays.asList(tizhi);
            Collections.shuffle(list);

            model.addAttribute("newtizhi", list);
            model.addAttribute("charp", charp);
            model.addAttribute("tick", tick);
        }

        //判断题
        if (panduan == 0) {
            BoolModel timu = tiKuMapper.BoolselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 0);


            String[] tizhi = new String[2];
            tizhi[0] = timu.getA();
            tizhi[1] = timu.getB();


            List<Comparable> list = Arrays.asList(tizhi);
            Collections.shuffle(list);

            model.addAttribute("charp", charp);
            model.addAttribute("newtizhi", list);
            model.addAttribute("tick", tick);

        }
        //填空
        if (panduan == 2) {
            TiankongModel timu = tiKuMapper.TiankongselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 2);
            model.addAttribute("charp", charp);
            model.addAttribute("tick", tick);
        }

        //基于图片的题目
        if (panduan == 3) {
            ResultfenxiModel timu = tiKuMapper.ResultfenxiselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 3);
            model.addAttribute("charp", charp);
            model.addAttribute("tick", tick);
        }
        model.addAttribute("tiaozhanid", tiaozhanid);
        return "tiaozhan";
    }

    /**
     * 进入题目
     *
     * @param tid
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/singletimu/{tid}")
    public String getTimu(@PathVariable("tid") int tid,
                          HttpSession session,
                          Model model,
                          RedirectAttributes attr
    ) {

        //判断题型
        int panduan = tiKuMapper.judgeTixin(tid);

        Date date = new Date();
        Timestamp sqlDate_ori = new Timestamp(date.getTime());

        /*
        try {
            session.getAttribute("username").toString();
        }catch (Exception e)
        {


            //选择题
            if (panduan==1){
                ChoiceModel timu=tiKuMapper.ChoiceselecttimunyTid(tid);
                model.addAttribute("timu",timu);
                model.addAttribute("tixin",1);
                System.out.println(timu);

                String[] tizhi = new String[4];
                tizhi[0]=timu.getA();
                tizhi[1]=timu.getB();
                tizhi[2]=timu.getC();
                tizhi[3]=timu.getD();

                List<Comparable> list = Arrays.asList(tizhi);
                Collections.shuffle(list);


                System.out.println(list);
                model.addAttribute("newtizhi",list);
                return "singleTimu";
            }

            //判断题
            if (panduan==0){
                BoolModel timu=tiKuMapper.BoolselecttimunyTid(tid);
                model.addAttribute("timu",timu);
                model.addAttribute("tixin",0);


                String[] tizhi = new String[2];
                tizhi[0]=timu.getA();
                tizhi[1]=timu.getB();


                List<Comparable> list = Arrays.asList(tizhi);
                Collections.shuffle(list);


                model.addAttribute("newtizhi",list);
                return "singleTimu";
            }

                //填空题
            if (panduan==2){
                TiankongModel timu=tiKuMapper.TiankongselecttimunyTid(tid);
                model.addAttribute("timu",timu);
                model.addAttribute("tixin",2);

                return "singleTimu";
            }
            //图
            if (panduan==3){
                ResultfenxiModel timu=tiKuMapper.ResultfenxiselecttimunyTid(tid);
                model.addAttribute("timu",timu);
                model.addAttribute("tixin",3);
                return "singleTimu";
            }

            return "singleTimu";
        }

         */
        String username = session.getAttribute("username").toString();
        model.addAttribute("username", username);
        model.addAttribute("oritime", sqlDate_ori);

        //章节控制
        int charp = tiKuMapper.SelectCharpfromTid(tid);
        try {
            List<Integer> fcl = personalAccuracyMapper.findAllisSuccess(username);
            int maxC = Collections.max(fcl);
            if (charp != 1) {
                if (charp > maxC + 1) {
                    attr.addFlashAttribute("message", "您尚未解锁该章权限");
                    return "redirect:/tiku";
                }
            }
        }catch (Exception e){
            if (charp != 1){
                attr.addFlashAttribute("message", "您尚未解锁该章权限");
                return "redirect:/tiku";
            }
        }


        //选择题
        if (panduan == 1) {
            ChoiceModel timu = tiKuMapper.ChoiceselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 1);

            String[] tizhi = new String[4];
            tizhi[0] = timu.getA();
            tizhi[1] = timu.getB();
            tizhi[2] = timu.getC();
            tizhi[3] = timu.getD();

            List<Comparable> list = Arrays.asList(tizhi);
            Collections.shuffle(list);


            model.addAttribute("newtizhi", list);
            return "singleTimu";
        }

        //判断题
        if (panduan == 0) {
            BoolModel timu = tiKuMapper.BoolselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 0);


            String[] tizhi = new String[2];
            tizhi[0] = timu.getA();
            tizhi[1] = timu.getB();


            List<Comparable> list = Arrays.asList(tizhi);
            Collections.shuffle(list);


            model.addAttribute("newtizhi", list);
            return "singleTimu";
        }

        //填空题
        if (panduan == 2) {
            TiankongModel timu = tiKuMapper.TiankongselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 2);

            return "singleTimu";
        }
        //图
        if (panduan == 3) {
            ResultfenxiModel timu = tiKuMapper.ResultfenxiselecttimunyTid(tid);
            model.addAttribute("timu", timu);
            model.addAttribute("tixin", 3);
            return "singleTimu";
        }
        model.addAttribute("message", "您尚未答题");

        return "singleTimu";
    }
}
