package com.example.fengkou_2.ZhishikuBag.Model;

import com.example.fengkou_2.TiKuBag.Model.BaseOriTikuModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZhishiModel {
    private int id;
    private String Miaoshu;
    private int ParentID;
    private int bianma;
    private int zhang;
    private int jie;
}
*/

/**
 * 知识基类 -》 知识条类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZhishiModel extends BaseZhishiOriModel {
    private String Miaoshu;
    private int ParentID;
    private int bianma;
}