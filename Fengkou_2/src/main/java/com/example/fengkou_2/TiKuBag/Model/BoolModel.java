package com.example.fengkou_2.TiKuBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoolModel {
    private int id;
    private int Tikuid;
    private String A;
    private String B;
    private String Tigan;
    private String ans;
}
*/

/**
 * 基类 -》 填空类 -》 判断类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoolModel extends TiankongModel{
    private String A;
    private String B;
}