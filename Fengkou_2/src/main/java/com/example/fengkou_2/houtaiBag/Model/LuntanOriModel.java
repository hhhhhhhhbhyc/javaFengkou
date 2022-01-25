package com.example.fengkou_2.houtaiBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LuntanOriModel {
    private int id;
    private int tikuid;
    private String username;
    private String somecontext;
    private Date dealtime;
    private int likes;
}
