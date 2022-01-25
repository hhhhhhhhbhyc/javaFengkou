package com.example.fengkou_2.RequestBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 外联爬虫类 ---- 谷粉学术
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel {
    private String title;
    private String href;
    private String auther;
    private String describe;
    private String pdf;
}
