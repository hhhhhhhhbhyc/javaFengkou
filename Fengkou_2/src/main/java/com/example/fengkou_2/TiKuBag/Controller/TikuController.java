package com.example.fengkou_2.TiKuBag.Controller;

import com.example.fengkou_2.PersonalBag.Mapper.PersonalAccuracyMapper;
import com.example.fengkou_2.TiKuBag.Mapper.TiKuMapper;
import com.example.fengkou_2.TiKuBag.Model.TikuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TikuController {
    @Autowired
    private TiKuMapper tiKuMapper;
    @Autowired
    private PersonalAccuracyMapper personalAccuracyMapper;

    /**
     * 按章节选择
     *
     * @param model
     * @param charp
     * @return
     */
    @GetMapping("/tiku/{charp}/{pter}")
    public String getTiKubychar(Model model,
                                @PathVariable("charp") int charp,
                                @PathVariable("pter") int pter
    ) {
        List<TikuModel> tuku = tiKuMapper.selecttimubyzhangjie(charp, pter);
        model.addAttribute("tiku", tuku);
        return "tiKu";
    }

    /**
     * 默认全部界面
     *
     * @param model
     * @return
     */
    @GetMapping("/tiku")
    public String getTiKu(Model model) {
        List<TikuModel> tuku = tiKuMapper.selectAlltimu();
        model.addAttribute("tiku", tuku);
        return "tiKu";
    }

    /**
     * 随机一题
     *
     * @param model
     * @return
     */
    @GetMapping("/tikuRandom")
    public String randomTiKu(Model model) {
        List<TikuModel> tuku = tiKuMapper.selectRandomtimu();
        model.addAttribute("tiku", tuku);
        return "tiKu";
    }

    /**
     * 自定义推荐
     *
     * @param model
     * @return
     */
    @GetMapping("/tikurefer")
    public String referTiKu(Model model,
                            HttpSession session) {
        String username = session.getAttribute("username").toString();
        try {
            List<Float> avgpartof = personalAccuracyMapper.selectpartofAccuracy(username);
            List<Integer> avgpartof_2 = personalAccuracyMapper.selectpartofAccuracy_2(username);

            int i_min = 0;
            for (int i = 0; i < avgpartof.size(); i++) {
                if (avgpartof.get(i) < avgpartof.get(i_min)) {
                    i_min = i;
                }
            }
            int small_part = avgpartof_2.get(i_min);
            //选择最需要的章节3题
            List<TikuModel> tuku = tiKuMapper.selecttimubyzhang(small_part);
            model.addAttribute("tiku", tuku);
            return "tiKu";
        } catch (Exception e) {
            return "redirect:/personalcharts";
        }

    }
}
