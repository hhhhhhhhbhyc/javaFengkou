package com.example.fengkou_2.EchartsBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 目前专用于图表
 */
public class PersonalansRatioModel {
    private Date dealdate;
    private int isCorrect;
}
