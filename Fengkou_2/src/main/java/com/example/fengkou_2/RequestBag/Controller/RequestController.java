package com.example.fengkou_2.RequestBag.Controller;

import com.example.fengkou_2.RequestBag.Model.RequestModel;
import com.example.fengkou_2.Util.RequestUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Controller
public class RequestController {
    /**
     * 爬虫接口，但目前没有用
     * @return
     */
    @PostMapping("/get")
    public String getSome(Model model,
                          @RequestParam("choice")String choice,
                          RedirectAttributes attr
                          ){
        Document document=RequestUtil.bigtest("https://xs2.dailyheadlines.cc/scholar?q="+choice);
        //System.out.println(document);

        List<RequestModel> lr = new ArrayList<>();

        Elements elements = document.getElementsByClass("gs_r gs_or gs_scl");
        for (Element el:elements){
            RequestModel requestModel = new RequestModel();

            Elements e1=el.getElementsByClass("gs_rt");
            String title = e1.text();
            requestModel.setTitle(title);

            String href =e1.select("a").attr("href");
            requestModel.setHref(href);


            Elements e2=el.getElementsByClass("gs_a");
            String auther = e2.text();
            requestModel.setAuther(auther);

            Elements e3=el.getElementsByClass("gs_rs");
            String describe = e3.text();
            requestModel.setDescribe(describe);

            Elements e4=el.getElementsByClass("gs_ggs gs_fl");
            String pdf = e4.select("div").select("div").select("a").attr("href");
            requestModel.setPdf(pdf);

            lr.add(requestModel);
        }
        attr.addFlashAttribute("lr",lr.subList(0,10));
        System.out.println(lr);
        return "redirect:/tiku";
    }




}
