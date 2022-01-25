package com.example.fengkou_2.PersonalBag.Model;

import com.example.fengkou_2.LoginRegoster.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LargeUserModel {
    private int uid;
    private String username;
    private String password;
    private String email;
    private String workplace;
    private String realname;
    private String address;
    private int sex;
    private String pic;
}
*/

/**
 * 继承自 User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LargeUserModel extends User {
    private String workplace;
    private String realname;
    private String address;
    private int sex;
    private String pic;
}