package com.example.fengkou_2.TiKuBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceModel {
    private int id;
    private int Tikuid;
    private String A;
    private String B;
    private String C;
    private String D;
    private String Tigan;
    private String ans;
}
 */

/**
 * 基类 -》 填空类 -》 判断类 -》 选择类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceModel extends BoolModel {
    private String C;
    private String D;
}