package com.example.fengkou_2.houtaiBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题库类 后台表现形式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TikuOriModel {
    private int tid;
    private int tixin;
    private int zhangjieid;
    private int nandu;
    private float passratio;
    private String tiganjianchen;
}
