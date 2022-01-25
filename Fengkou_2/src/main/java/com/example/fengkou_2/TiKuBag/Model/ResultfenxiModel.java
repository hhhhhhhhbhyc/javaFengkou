package com.example.fengkou_2.TiKuBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultfenxiModel {
    private int id;
    private int tikuid;
    private String pic;
    private String ans;
}

 */

/**
 * 图像题 ：基类 -》 图像类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultfenxiModel extends BaseOriTikuModel{
    private String pic;
    private String ans;
}
