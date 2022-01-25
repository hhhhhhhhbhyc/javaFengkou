package com.example.fengkou_2.ZhishikuBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZhishidianModel {
    private int id;
    private String zhishidian;
    private int zhangjieid;
    private int zhang;
    private int jie;
}
*/

/**
 * 知识基类 -> 知识点类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZhishidianModel extends BaseZhishiOriModel {
    private String zhishidian;
    private int zhangjieid;
}