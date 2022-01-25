package com.example.fengkou_2.TiKuBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiankongModel {
    private int id;
    private int tikuid;
    private String tigan;
    private String ans;
}
*/

/**
 * 基类 -》 填空类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiankongModel extends BaseOriTikuModel {
    private String tigan;
    private String ans;
}