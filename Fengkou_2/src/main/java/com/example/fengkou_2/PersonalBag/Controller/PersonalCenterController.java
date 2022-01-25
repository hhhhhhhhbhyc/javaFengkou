package com.example.fengkou_2.PersonalBag.Controller;

import com.example.fengkou_2.PersonalBag.Mapper.PersonalAccuracyMapper;
import com.example.fengkou_2.PersonalBag.Mapper.UserFufillMapper;
import com.example.fengkou_2.PersonalBag.Model.LargeUserModel;
import com.example.fengkou_2.PersonalBag.Model.LetterModel;
import com.example.fengkou_2.PersonalBag.Model.TiaozhanModel;
import com.example.fengkou_2.PersonalBag.Model.UsersheetanswerModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class PersonalCenterController {
    @Autowired
    private PersonalAccuracyMapper personalAccuracyMapper;
    @Autowired
    private UserFufillMapper userFufillMapper;

    /**
     * 个人信息页面--默认
     *
     * @return
     */
    @RequestMapping("/personalcenter")
    private String getPersonal(Model model,
                               HttpSession session) {
        String username = session.getAttribute("username").toString();
        LargeUserModel largeUserModel = userFufillMapper.selectAlldescribebyUser(username);
        model.addAttribute("largeuser", largeUserModel);
        model.addAttribute("change", 0);
        return "PersonalCenter";
    }

    /**
     * 个人信息页面--修改
     *
     * @return
     */
    @RequestMapping("/personalcenterch")
    private String chgetPersonal(Model model) {
        model.addAttribute("change", 1);
        return "PersonalCenter";
    }

    /**
     * 调用自由编译器
     *
     * @return
     */
    @RequestMapping("/personalbianyi")
    private String getbianyi() {
        return "Personalbianyi";
    }

    /**
     * 个人学习图表
     *
     * @return
     */
    @RequestMapping("/personalcharts")
    private String getCharts(Model model,
                             HttpSession session) {
        String username = session.getAttribute("username").toString();

        //正确率
        List<Float> avgdayacc = personalAccuracyMapper.selectdayaccuracy(username);
        model.addAttribute("avgdayacc", avgdayacc);
        //时间
        List<Date> avgdayacc_2 = personalAccuracyMapper.selectdayaccuracy_2(username);


        model.addAttribute("avgdayacc_2", avgdayacc_2);

        List<Float> avgpartof = personalAccuracyMapper.selectpartofAccuracy(username);
        List<Integer> avgpartof_2 = personalAccuracyMapper.selectpartofAccuracy_2(username);


        model.addAttribute("avgpartof", avgpartof);
        model.addAttribute("avgpartof_2", avgpartof_2);

        List<UsersheetanswerModel> usatop10 = personalAccuracyMapper.selecttop10(username);

        model.addAttribute("top", usatop10);
        return "Personalcharts";
    }

    /**
     * 个人挑战页面
     *
     * @return
     */
    @RequestMapping("/personaltiaozhan")
    private String getPersonaltiaozhan(Model model,
                                       HttpSession session) {
        String username = session.getAttribute("username").toString();
        List<TiaozhanModel> tiaozhanModel = personalAccuracyMapper.selectTiaozhan();

        List<Integer> fliter = personalAccuracyMapper.selectTiaozhanbyuser(username);

        for (Integer fli : fliter) {
            tiaozhanModel.get(fli - 1).setIsSuccess(1);
        }
        model.addAttribute("tiaozhan", tiaozhanModel);

        List<LetterModel> letters = personalAccuracyMapper.selectLetterbyUser(username);
        model.addAttribute("letters", letters);

        return "PersonalTiaozhan";
    }


}