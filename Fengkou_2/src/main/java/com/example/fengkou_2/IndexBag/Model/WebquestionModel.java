package com.example.fengkou_2.IndexBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebquestionModel {
    private int id;
    private String question;
    private String ans;
    private int quan;
}
