package com.example.fengkou_2.PersonalBag.Mapper;

import com.example.fengkou_2.PersonalBag.Model.LetterModel;
import com.example.fengkou_2.PersonalBag.Model.TiaozhanModel;
import com.example.fengkou_2.PersonalBag.Model.UsersheetanswerModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface PersonalAccuracyMapper {
    /**
     * 查询个人历史答题记录--正确率
     *
     * @param username
     * @return
     */
    @Select("select " +
            " avg(iscorrect) from useranswersheet " +
            "where uid=#{uid,jdbcType=VARCHAR} " +
            "group by date_format(dealdate, '%Y-%m-%d')")
    List<Float> selectdayaccuracy(String username);

    /**
     * 查询个人历史答题记录--时间
     *
     * @param username
     * @return
     */
    @Select("select date_format(dealdate, '%Y-%m-%d') dat" +
            "  from useranswersheet " +
            "where uid=#{uid,jdbcType=VARCHAR} " +
            "group by date_format(dealdate, '%Y-%m-%d') ")
    List<Date> selectdayaccuracy_2(String username);


    /**
     * 查询个人各版块答题正确率
     *
     * @param username
     * @return
     */
    @Select("select avg(iscorrect)  " +
            "  from useranswersheet,tiku,zhangjie " +
            "where uid=#{uid,jdbcType=VARCHAR}" +
            " and useranswersheet.tid= tiku.tid and zhangjie.id = tiku.zhangjieid  " +
            "group by zhangjie.zhang ")
    List<Float> selectpartofAccuracy(String username);

    /**
     * 查询个人各版块答题正确率--伴随章节
     *
     * @param username
     * @return
     */
    @Select("select zhangjie.zhang " +
            "from useranswersheet,tiku,zhangjie " +
            "where uid=#{uid,jdbcType=VARCHAR}" +
            " and useranswersheet.tid= tiku.tid and zhangjie.id = tiku.zhangjieid " +
            " group by zhangjie.zhang   ")
    List<Integer> selectpartofAccuracy_2(String username);

    /**
     * 挑战展示
     *
     * @param
     * @return
     */
    @Select("select tiaozhan.id " +
            "from tiaozhan,usertiaozhan where usertiaozhan.tiaozhanid = tiaozhan.id and" +
            " usertiaozhan.username = #{username,jdbcType=VARCHAR} " +
            "and usertiaozhan.issuccess= 1 ")
    List<Integer> selectTiaozhanbyuser(String username);

    /**
     * 挑战展示
     *
     * @param
     * @return
     */
    @Select("select * from tiaozhan")
    List<TiaozhanModel> selectTiaozhan();

    /**
     * 历史答题展示
     * @param username
     * @return
     */
    @Select("select useranswersheet.iscorrect,useranswersheet.tid,tiku.tiganjianchen," +
            "tiku.nandu,useranswersheet.dealdate,useranswersheet.doingtime from useranswersheet,tiku   " +
            "where useranswersheet.tid=tiku.tid and " +
            "useranswersheet.uid=#{username,jdbcType=VARCHAR} order by dealDate desc limit 15")
    List<UsersheetanswerModel> selecttop10(String username);


    @Select("select * from letter where username = #{username,jdbcType=VARCHAR}")
    List<LetterModel> selectLetterbyUser(String username);

    @Insert("insert into letter (title,context,time,username)" +
            "values ( #{title,jdbcType=VARCHAR}, " +
            "#{context,jdbcType=VARCHAR}," +
            "#{time,jdbcType=DATE}," +
            "#{username,jdbcType=VARCHAR})")
    void insertletter(String title,String context,Date time,String username);


    @Select("select DISTINCT tiaozhanid from usertiaozhan" +
            " where username =#{username,jdbcType=VARCHAR} ")
    List<Integer> findAllisSuccess(String username);
}
