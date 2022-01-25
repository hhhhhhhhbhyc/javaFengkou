package com.example.fengkou_2.LoginRegoster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户基类 User
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int Uid;
    private String username;
    private String password;
    private String email;
    private int auth;
}
