package com.example.fengkou_2.ZhishikuBag.Mapper;

import com.example.fengkou_2.TiKuBag.Model.TikuModel;
import com.example.fengkou_2.ZhishikuBag.Model.NonStructModel;
import com.example.fengkou_2.ZhishikuBag.Model.ZhishiModel;
import com.example.fengkou_2.ZhishikuBag.Model.ZhishidianModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZhishiMapper {
    /**
     * 查询所有知识条
     * @return
     */
    @Select("select zhishi.id,zhishi.Miaoshu," +
            "zhangjie.zhang,zhangjie.jie from zhishi,zhangjie " +
            "where zhishi.zhangjieId=zhangjie.id")
    List<ZhishiModel> selectAllzhishu();

    /**
     * 查询所有子知识条
     * @return
     */
    @Select("select zhishi.id,zhishi.Miaoshu,zhishi.parentid," +
            "zhangjie.zhang,zhangjie.jie from zhishi,zhangjie " +
            "where zhishi.zhangjieId=zhangjie.id and zhishi.parentid = #{pid,jdbcType=INTEGER}")
    List<ZhishiModel> selectAllzhishubyparentID(int id);

    /**
     * 查询所0级基础知识
     * @return
     */
    @Select("select id,Miaoshu " +
             "from zhishi " +
            "where parentID = 0")
    List<ZhishiModel> selectAllzhishu0();


    /**
     * 查询所有知识条--关键字法
     * @return
     */
    @Select("select zhishi.id,zhishi.miaoshu," +
            "zhishi.zhangjieId,zhangjie.zhang,zhangjie.jie" +
            " from zhishi,zhangjie where zhishi.miaoshu like #{choice,jdbcType=VARCHAR} and " +
            " zhishi.zhangjieId=zhangjie.id "
    )
    List<ZhishiModel> selectkeyzhishiku(String choice);


    /**
     * 查询所有知识点
     * @return
     */
    @Select("select zhishidian.id,zhishidian.zhishidian," +
            "zhishidian.zhangjieid,zhangjie.zhang,zhangjie.jie" +
            " from zhishidian,zhangjie where zhishidian.zhangjieid=zhangjie.id")
    List<ZhishidianModel> selectAllzhishidian();


    /**
     * 查询所有知识点--关键字法
     * @return
     */
    @Select("select zhishidian.id,zhishidian.zhishidian," +
            "zhishidian.zhangjieid,zhangjie.zhang,zhangjie.jie" +
            " from zhishidian,zhangjie where zhishidian.zhishidian like #{choice,jdbcType=VARCHAR} and " +
            " zhishidian.zhangjieid=zhangjie.id "
    )
    List<ZhishidianModel> selectkeyzhishidian(String choice);

    /**
     * 从题库到知识
     * @param tid
     * @return
     */
    @Select("select zhishi.id,zhishi.miaoshu," +
            "zhishi.zhangjieid,zhangjie.zhang,zhangjie.jie" +
            " from zhishi,zhangjie,tiku where tiku.tid = #{tid,jdbcType=INTEGER} and " +
            " zhishi.zhangjieid=zhangjie.id and tiku.zhangjieid=zhangjie.id "
    )
    List<ZhishiModel> tiku2zhihsi(int tid);

    /**
     * 非结构知识 查询一个
     * @return
     */
    @Select("select * from nonstructzhishi order by rand() limit 1")
    NonStructModel selectnonstruct();

    /**
     * 非结构知识 查询一个
     * @return
     */
    @Select("select * from nonstructzhishi where nonstructzhishi.title like #{title,jdbcType=VARCHAR}  order by rand() limit 1 ")
    NonStructModel selectnonstructbyTitle(String title);

    /**
     * 非结构知识 发表
     * @param context
     * @param title
     * @param auther
     */
    @Insert("insert into nonstructzhishi (context,title,auther)" +
            "values ( #{context,jdbcType=VARCHAR}, " +
            "#{title,jdbcType=VARCHAR}, " +
            "#{auther,jdbcType=VARCHAR})")
    void insertnonzhihsi(String context,String title,String auther);


    /**
     * 非结构知识 查询用户名
     * @return
     */
    @Select("select auther from nonstructzhishi where nonstructzhishi.id= #{id,jdbcType=INTEGER}")
    String selectnonstructbyUser(int id);
}
