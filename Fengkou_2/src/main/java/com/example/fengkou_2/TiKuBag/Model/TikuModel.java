package com.example.fengkou_2.TiKuBag.Model;

import com.example.fengkou_2.houtaiBag.Model.TikuOriModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题库 总类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TikuModel {
    private int Tid;
    private int TiXin;
    private int ZhishiID;
    private int ZhangjieId;
    private int Nandu;
    private float passRatio;
    private String tiGanjianchen;
    private int zhang;
    private int jie;
}

