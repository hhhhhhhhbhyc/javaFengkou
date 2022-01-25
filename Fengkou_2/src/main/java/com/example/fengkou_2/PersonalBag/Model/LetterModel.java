package com.example.fengkou_2.PersonalBag.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LetterModel {
    private int id;
    private String title;
    private String context;
    private String username;
    private Date time;
}
