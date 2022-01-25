package com.example.fengkou_2.TiKuBag.Mapper;

import com.example.fengkou_2.TiKuBag.Model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface TiKuMapper {

    @Select("select * from chioceanswer where tikuid=#{tid,jdbcType=INTEGER}")
    ChoiceModel selectChoicebyTikuid(int tid);

    @Select("select * from boolanswer where tikuid=#{tid,jdbcType=INTEGER}")
    BoolModel selectBoolbyTikuid(int tid);

    @Select("select * from tiankonganswer where tikuid=#{tid,jdbcType=INTEGER}")
    TiankongModel selectTiankongbyTikuid(int tid);

    @Select("select * from resultfenxianswer where tikuid=#{tid,jdbcType=INTEGER}")
    ResultfenxiModel selectFenxibyTikuid(int tid);

    /**
     * 随机抽取三道题目
     * @return
     */
    @Select("select tiku.tid,tiku.tixin,tiku.nandu,tiku.passratio," +
            "tiku.tiganjianchen,zhangjie.zhang,zhangjie.jie" +
            " from tiku,zhangjie where tiku.zhangjieid=zhangjie.id ORDER BY RAND() LIMIT 3 ")
    List<TikuModel> selectRandomtimu();


    /**
     * 随机抽取推荐章
     * @param zhang
     * @param
     * @return
     */
    @Select("select tiku.tid,tiku.tixin,tiku.nandu,tiku.passratio," +
            "tiku.tiganjianchen,zhangjie.zhang,zhangjie.jie" +
            " from tiku,zhangjie where tiku.zhangjieId = zhangjie.id " +
            "and zhangjie.zhang=#{zhang,jdbcType=INTEGER}  " +
            " ORDER BY RAND() LIMIT 3")
    List<TikuModel> selecttimubyzhang(int zhang);


    /**
     * 按章节查询题目
     * @return
     */
    @Select("select tiku.tid,tiku.tixin,tiku.nandu,tiku.passratio," +
            "tiku.tiganjianchen,zhangjie.zhang,zhangjie.jie" +
            " from tiku,zhangjie where tiku.zhangjieId = zhangjie.id " +
            "and zhangjie.zhang=#{zhang,jdbcType=INTEGER} and " +
            "zhangjie.jie=#{jie,jdbcType=INTEGER}")
    List<TikuModel> selecttimubyzhangjie(int zhang,int jie);

    /**
     * 查询所有题目
     * @return
     */
    @Select("select tiku.tid,tiku.tixin,tiku.nandu,tiku.passratio," +
            "tiku.tiganjianchen,zhangjie.zhang,zhangjie.jie" +
            " from tiku,zhangjie where tiku.zhangjieid=zhangjie.id ")
    List<TikuModel> selectAlltimu();


    /**
     * 判断题型
     * @param Tid
     * @return
     */
    @Select("select TiXin from tiku where Tid=#{tid,jdbcType=INTEGER}")
    int judgeTixin(int Tid);


    /**
     * 查询选择题目
     * @param tid
     * @return
     */
    @Select("select * from tiku,chioceanswer where Tid=#{tid,jdbcType=INTEGER}" +
            " and tiku.Tid=chioceanswer.Tikuid ")
    ChoiceModel ChoiceselecttimunyTid(int tid);

    /**
     * 查询判断题目
     * @param tid
     * @return
     */
    @Select("select * from tiku,boolanswer where Tid=#{tid,jdbcType=INTEGER}" +
            " and tiku.Tid=boolanswer.Tikuid ")
    BoolModel BoolselecttimunyTid(int tid);

    /**
     * 查询判断题目
     * @param tid
     * @return
     */
    @Select("select * from tiku,tiankonganswer where Tid=#{tid,jdbcType=INTEGER}" +
            " and tiku.Tid=tiankonganswer.Tikuid ")
    TiankongModel TiankongselecttimunyTid(int tid);

    /**
     * 更新个人答题记录
     * @param uid
     * @param tid
     * @param isCorrect
     * @param dealDate
     */
    @Insert("insert into useranswersheet (Uid,Tid,isCorrect,dealDate,doingTime)" +
            "values ( #{uid,jdbcType=VARCHAR}, " +
            "#{tid,jdbcType=INTEGER}, " +
            "#{isCorrect,jdbcType=INTEGER}, " +
            "#{dealDate,jdbcType=DATE}," +
            " #{doingtime,jdbcType=INTEGER}" +
            ")")
    void insert(String uid, String tid, int isCorrect, Date dealDate,int doingtime);

    /**
     * 查询基于图像题目
     * @param tid
     * @return
     */
    @Select("select * from tiku,resultfenxianswer where Tid=#{tid,jdbcType=INTEGER}" +
            " and tiku.Tid=resultfenxianswer.tikuid ")
    ResultfenxiModel ResultfenxiselecttimunyTid(int tid);


    @Select("select zhang from tiku,zhangjie where zhangjie.id = tiku.zhangjieid and" +
            " tiku.tid = #{tid,jdbcType=INTEGER}")
    int SelectCharpfromTid(int tid);
}
