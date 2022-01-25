package com.example.fengkou_2.PersonalBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersheetanswerModel {
    private int tid;
    private int iscorrect;
    private Date dealDate;
    private int doingTime;
    private String tiganjianchen;
    private int nandu;
}
