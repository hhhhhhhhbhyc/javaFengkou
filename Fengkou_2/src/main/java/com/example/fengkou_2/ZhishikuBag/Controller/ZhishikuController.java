package com.example.fengkou_2.ZhishikuBag.Controller;

import com.example.fengkou_2.ZhishikuBag.Mapper.ZhishiMapper;
import com.example.fengkou_2.ZhishikuBag.Model.NonStructModel;
import com.example.fengkou_2.ZhishikuBag.Model.ZhishiModel;
import com.example.fengkou_2.ZhishikuBag.Model.ZhishidianModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ZhishikuController {
    @Autowired
    private ZhishiMapper zhishiMapper;

    /**
     * 查询知识条目 - 父
     * @param model
     * @return
     */
    @GetMapping("/zhishiku")
    public String getZhishuku(Model model){
        List<ZhishiModel> zhishi =  zhishiMapper.selectAllzhishu0();
        model.addAttribute("zhishi",zhishi);
        return "zhishitiao";
    }

    /**
     * 查询知识条目 - 子
     * @param model
     * @return
     */
    @GetMapping("/zhishiku/{parentid}")
    public String getZhishukuzi(Model model,
                                @PathVariable("parentid")int parentid){
        List<ZhishiModel> zhishi =  zhishiMapper.selectAllzhishubyparentID(parentid);
        System.out.println(zhishi);
        model.addAttribute("zhishi",zhishi);
        return "zhishitiao";
    }


    @PostMapping("/choicezhishiku")
    public String choiceZhishuku(Model model,
                                   @RequestParam("choice")String choice){

        List<ZhishiModel> zhishi =  zhishiMapper.selectkeyzhishiku("%"+choice+"%");
        //System.out.println(zhishi);
        model.addAttribute("zhishi",zhishi);
        return "zhishitiao";
    }

    /**
     * 查询知识点
     * @param model
     * @return
     */
    @GetMapping("/zhishidian")
    public String getZhishudian(Model model){
        List<ZhishidianModel> zhishidian =  zhishiMapper.selectAllzhishidian();
        model.addAttribute("zhishidian",zhishidian);
        return "zhishiku";
    }

    @PostMapping("/choicezhishidian")
    public String choiceZhishudian(Model model,
                                   @RequestParam("choice")String choice){

        List<ZhishidianModel> zhishidian =  zhishiMapper.selectkeyzhishidian("%"+choice+"%");
        model.addAttribute("zhishidian",zhishidian);
        return "zhishiku";
    }

    /**
     * 题库-》知识
     * @param model
     * @param tid
     * @param attr
     * @return
     */
    @GetMapping("/tiku2zhishi/{tid}")
    public String tiku2zhishi(Model model,
                              @PathVariable("tid") int tid,
                              RedirectAttributes attr){

        List<ZhishiModel> zhishi =  zhishiMapper.tiku2zhihsi(tid);
        //System.out.println(zhishi);
        attr.addFlashAttribute("tiku2zhishiS",zhishi);
        return "redirect:/singletimu/"+tid;
    }


    @GetMapping("/nonstruct")
    public String getnonStruct(Model model,
                              RedirectAttributes attr){

        NonStructModel zhishi =  zhishiMapper.selectnonstruct();
        //System.out.println(zhishi);
        model.addAttribute("zhishi",zhishi);
        return "zhishinonstruct";
    }

    @PostMapping("/nonstruct")
    public String postnonStruct(Model model,
                                HttpSession session,
                                @RequestParam("title")String title,
                                @RequestParam("context")String context
                                ){
        String username = session.getAttribute("username").toString();
        zhishiMapper.insertnonzhihsi(context,title,username);

        return "redirect:/nonstruct";
    }

    @PostMapping("/nonstructquery")
    public String choicenonStruct(Model model,
                                @RequestParam("choice")String title
    ){
        NonStructModel zhishi = zhishiMapper.selectnonstructbyTitle('%'+title+'%');
        model.addAttribute("zhishi",zhishi);
        return "zhishinonstruct";
    }

}
