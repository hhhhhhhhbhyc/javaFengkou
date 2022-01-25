package com.example.fengkou_2.LuntanBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LuntanModel {
    private int id;
    private int Tikuid;
    private String username;
    private String somecontext;
    private Date dealTime;
    private String pic;
    private int likes;
}
