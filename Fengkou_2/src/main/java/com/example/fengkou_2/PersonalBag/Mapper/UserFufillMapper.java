package com.example.fengkou_2.PersonalBag.Mapper;

import com.example.fengkou_2.PersonalBag.Model.LargeUserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserFufillMapper {
    /**
     * 查询用户详细信息
     * @param username
     * @return
     */
    @Select("select * from user where user.username =#{username,jdbcType=VARCHAR} ")
    LargeUserModel selectAlldescribebyUser(String username);

    /**
     * 更新用户详细信息
     * @param realname
     * @param workplace
     * @param address
     * @param sex
     * @param pic
     * @param username
     */
    @Update(" update user" +
            "   set realname = #{realname,jdbcType=VARCHAR}," +
            " workplace=#{workplace,jdbcType=VARCHAR}, " +
            " address=#{address,jdbcType=VARCHAR}, " +
            " sex=#{sex,jdbcType=INTEGER}, " +
            " pic=#{pic,jdbcType=VARCHAR} " +
            " where username = #{username,jdbcType=VARCHAR}"
    )
    void updateuser(String realname,String workplace,String address,int sex,String pic,String username);
}
