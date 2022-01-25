package com.example.fengkou_2.ZhishikuBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 非结构知识类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonStructModel {
    private int id;
    private String context;
    private String title;
    private String auther;
}
