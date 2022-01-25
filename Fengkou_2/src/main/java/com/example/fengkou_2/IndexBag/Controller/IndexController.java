package com.example.fengkou_2.IndexBag.Controller;

import com.example.fengkou_2.IndexBag.Model.HezuohuobanModel;
import com.example.fengkou_2.IndexBag.Model.WebquestionModel;
import com.example.fengkou_2.IndexBag.Model.Zhangjie;
import com.example.fengkou_2.IndexBag.mapper.IndexMapper;
import com.example.fengkou_2.IndexBag.mapper.ZhangjieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ZhangjieMapper zhangjieMapper;
    @Autowired
    private IndexMapper indexMapper;

    /**
     * 获取首页信息
     * @param model
     * @param session
     * @return
     */
    @Cacheable(cacheNames = "index")
    @GetMapping("/")
    public String getIndex(Model model,
                           HttpSession session){
        List<Zhangjie> zhangjie= zhangjieMapper.selectallFromZhangjie();

        model.addAttribute("zhangjie", zhangjie);
        try {
            session.getAttribute("username").toString();

        }catch (Exception e){
            List<WebquestionModel> Webq = indexMapper.selecwebQ();
            model.addAttribute("webq",Webq);

            List<HezuohuobanModel> zzhb = indexMapper.selecthezuohuoban();
            model.addAttribute("zzhb",zzhb);
            return "index";
        }



        String username = session.getAttribute("username").toString();
        model.addAttribute("username", username);

        List<WebquestionModel> Webq = indexMapper.selecwebQ();
        model.addAttribute("webq",Webq);

        List<HezuohuobanModel> zzhb = indexMapper.selecthezuohuoban();
        model.addAttribute("zzhb",zzhb);

        return "index";
    }
}
