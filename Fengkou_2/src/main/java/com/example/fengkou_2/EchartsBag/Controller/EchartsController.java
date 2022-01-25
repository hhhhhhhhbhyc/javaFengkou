package com.example.fengkou_2.EchartsBag.Controller;

import com.example.fengkou_2.EchartsBag.Mapper.PersonalRatioaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EchartsController {

    @Autowired
    private PersonalRatioaMapper personalRatioaMapper;

    @GetMapping("/echarts")
    private String getEcharts(Model model){

       List<Object> data = personalRatioaMapper.selectAlldata();
       model.addAttribute("data",data);
       System.out.println(data);
       return "Echartstest";
    }
}
