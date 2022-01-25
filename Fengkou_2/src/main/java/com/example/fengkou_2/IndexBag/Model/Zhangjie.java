package com.example.fengkou_2.IndexBag.Model;

import com.example.fengkou_2.ZhishikuBag.Model.BaseZhishiOriModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zhangjie {
    private int id;
    private int zhang;
    private int jie;
    private String miaoshu;
    private String path;
    private String mengmianpath;
}
*/
/**
 * 章节类 继承自 知识基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zhangjie extends BaseZhishiOriModel {
    private String miaoshu;
    private String path;
    private String mengmianpath;
}