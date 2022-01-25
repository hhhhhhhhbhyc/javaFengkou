package com.example.fengkou_2.MarkdownBag.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarkdownController {
    @GetMapping("/md")
    public String toEditor(){
        return "markdowntest";
    }

}
