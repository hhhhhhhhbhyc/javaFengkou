package com.example.fengkou_2.TiKuBag.Mapper;

import com.example.fengkou_2.TiKuBag.Model.TikuModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TiaozhanMapper {
    /**
     * 随机抽取一个题目
     * @return
     */
    @Select("select tiku.tid from tiku,zhangjie where tiku.zhangjieid = zhangjie.id and " +
            "zhangjie.zhang=#{zhang,jdbcType=INTEGER} order by rand() limit 1")
    Integer shuffleTikuByZhang(int zhang);

    /**
     * 成就录入
     * @param username
     * @param tiaozhanID
     * @param isSuccess
     */
    @Insert("insert into usertiaozhan (username,tiaozhanid,isSuccess)" +
            "values ( #{username,jdbcType=VARCHAR}, " +
            "#{tiaozhanID,jdbcType=INTEGER}, " +
            "#{isSuccess,jdbcType=INTEGER})")
    void insertTiaozhan(String username,int tiaozhanID,int isSuccess);

}
