package com.example.fengkou_2.LoginRegoster.Mapper;

import com.example.fengkou_2.LoginRegoster.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 注册后，插入用户
     * @param username
     * @param password
     * @param email
     */
    @Insert("insert into user (username,password,email)" +
            "values ( #{username,jdbcType=VARCHAR}, " +
            "#{password,jdbcType=CHAR}, " +
            "#{email,jdbcType=VARCHAR} " +
            ")")
    void insert(String username,String password,String email);

    /**
     * 判断用户用户名密码是否存在
     * @param username
     * @return
     */
    @Select("select password from user " +
            "where " +
            " username=#{username,jdbcType=VARCHAR}"
    )
    String selectforloginJudje(String username);

    /**
     * 查询用户邮箱
     * @param username
     * @return
     */
    @Select("select email from user " +
            "where " +
            " username=#{username,jdbcType=VARCHAR}"
    )
    String selectemailforloginJudje(String username);

    /**
     *  更新密码
     * @param password
     * @param username
     */
    @Update(" update user " +
            "   set password = #{password,jdbcType=VARCHAR} " +
            " where " +
            " username=#{username,jdbcType=VARCHAR}"
    )
    void updateMiusPro(String password,String username);



    @Select("select auth from user" +
            " where user.username= #{username,jdbcType=VARCHAR}" +
            " and password=#{password,jdbcType=VARCHAR}")
    int judgeAuth(String username,String password);
}
