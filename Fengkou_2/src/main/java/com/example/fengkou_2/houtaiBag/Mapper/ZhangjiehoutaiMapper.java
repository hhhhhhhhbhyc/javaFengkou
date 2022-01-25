package com.example.fengkou_2.houtaiBag.Mapper;

import com.example.fengkou_2.IndexBag.Model.HezuohuobanModel;
import com.example.fengkou_2.IndexBag.Model.Zhangjie;
import com.example.fengkou_2.LoginRegoster.Model.User;
import com.example.fengkou_2.ZhishikuBag.Model.NonStructModel;
import com.example.fengkou_2.houtaiBag.Model.LuntanOriModel;
import com.example.fengkou_2.houtaiBag.Model.TikuOriModel;
import com.example.fengkou_2.houtaiBag.Model.ZhishiOriModel;
import com.example.fengkou_2.houtaiBag.Model.ZhishitiaoOriModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZhangjiehoutaiMapper {
    /**
     * 查一个
     * @param zhangjie
     * @return
     */
    @Select("select * from zhangjie where zhangjie.id=#{zid,jdbcType=INTEGER}")
    Zhangjie selectZhangjie(int zhangjie);

    /**
     * 删
     * @param zid
     */
    @Delete("delete from zhangjie where zhangjie.id=#{zid,jdbcType=INTEGER}")
    void delZhang(int zid);

    /**
     * 修改
     * @param zhang
     * @param jie
     * @param miaoshu
     * @param path
     * @param mengmianpath
     * @param id
     */
    @Update(" update zhangjie" +
            "  set zhang = #{zhang,jdbcType=INTEGER}, " +
            "  jie= #{jie,jdbcType=INTEGER},"+
            "  miaoshu= #{miaoshu,jdbcType=VARCHAR},"+
            "  path= #{path,jdbcType=VARCHAR},"+
            "  mengmianpath= #{mengmianpath,jdbcType=VARCHAR}" +
            " where id=#{id,jdbcType=INTEGER}"
    )
    void updateZhang(int zhang,int jie,String miaoshu,String path,String mengmianpath,int id);


    @Insert("insert into zhangjie(zhang,jie,miaoshu,path,mengmianpath)" +
            "values (#{zhang,jdbcType=INTEGER}," +
            "#{jie,jdbcType=INTEGER}," +
            "#{miaoshu,jdbcType=VARCHAR},"+
            "#{path,jdbcType=VARCHAR},"+
            "#{mengmianpath,jdbcType=VARCHAR})"
    )
    void addZhang(int zhang,int jie,String miaoshu,String path,String mengmianpath);

    //-----------------------------------------------------------
    @Select("select * from tiku")
    List<TikuOriModel> selectAlltiku();

    /**
     * 删
     * @param tid
     */
    @Delete("delete from tiku where tiku.tid=#{tid,jdbcType=INTEGER}")
    void deltiku(int tid);

    @Insert("insert into tiku(tixin,zhangjieid,nandu,passratio,tiganjianchen)" +
            "values (#{tixin,jdbcType=INTEGER}," +
            "#{zhangjieid,jdbcType=INTEGER}," +
            "#{nandu,jdbcType=INTEGER},"+
            "#{passratio,jdbcType=FLOAT},"+
            "#{tiganjianchen,jdbcType=VARCHAR})"
    )
    void addtiku(int tixin,int zhangjieid,int nandu,Float passratio,String tiganjianchen);

    /**
     * 查一个
     * @param tid
     * @return
     */
    @Select("select * from tiku where tiku.tid=#{tid,jdbcType=INTEGER}")
    TikuOriModel selecttiku(int tid);


    @Update(" update tiku" +
            "  set tixin = #{tixin,jdbcType=INTEGER}, " +
            "  zhangjieid= #{zhangjieid,jdbcType=INTEGER},"+
            "  nandu= #{nandu,jdbcType=INTEGER},"+
            "  passratio= #{passratio,jdbcType=FLOAT},"+
            "  tiganjianchen= #{tiganjianchen,jdbcType=VARCHAR}" +
            " where tid=#{tid,jdbcType=INTEGER}"
    )
    void updatetiku(int tixin,int zhangjieid,int nandu,Float passratio,String tiganjianchen,int tid);






    //-----------------------------------------------------------------

    @Select("select * from zhishidian")
    List<ZhishiOriModel> selectAllzhishiku();

    /**
     * 删
     * @param
     */
    @Delete("delete from zhishidian where zhishidian.id=#{zid,jdbcType=INTEGER}")
    void delzhishiku(int zid);

    @Insert("insert into zhishidian(zhishidian,zhangjieid)" +
            "values (#{zhishidian,jdbcType=VARCHAR}," +
            "#{zhangjieid,jdbcType=INTEGER})"
    )
    void addzhishiku(String zhishidian,int zhangjieid);

    /**
     * 查一个
     * @param id
     * @return
     */
    @Select("select * from zhishidian where zhishidian.id=#{zid,jdbcType=INTEGER}")
    ZhishiOriModel selectzhishiku(int zid);


    @Update(" update zhishidian" +
            "  set zhishidian = #{zhishidian,jdbcType=VARCHAR}, " +
            "  zhangjieid= #{zhangjieid,jdbcType=INTEGER}"+
            " where id=#{zid,jdbcType=INTEGER}"
    )
    void updatezhishiku(String zhishidian,int zhangjieid,int zid);

    //--------------------------------------------------------------------------------

    /**
     * 查论坛
     * @return
     */
    @Select("select * from luntan")
    List<LuntanOriModel> selectAllluntan();

    /**
     * 删论坛
     * @param id
     */
    @Delete("delete from luntan where luntan.id=#{id,jdbcType=INTEGER}")
    void delluntan(int id);

    //----------------------------------------------------------------------
    /**
     * 查
     * @return
     */
    @Select("select * from nonstructzhishi")
    List<NonStructModel> selectAllnonzhishi();

    /**
     * 删
     * @param id
     */
    @Delete("delete from nonstructzhishi where nonstructzhishi.id=#{id,jdbcType=INTEGER}")
    void delnonzhishi(int id);

    //----------------------------------------------------------------------

    @Select("select * from hezuohuoban")
    List<HezuohuobanModel> selectAllhzhb();

    /**
     * 删
     * @param
     */
    @Delete("delete from hezuohuoban where hezuohuoban.id=#{id,jdbcType=INTEGER}")
    void delhzhb(int id);

    @Insert("insert into hezuohuoban(pic,title,wailian)" +
            "values (#{pic,jdbcType=VARCHAR}," +
            "#{title,jdbcType=VARCHAR}," +
            "#{wailian,jdbcType=VARCHAR})"
    )
    void addhzhb(String pic,String title,String wailian);

    /**
     * 查一个
     * @param id
     * @return
     */
    @Select("select * from hezuohuoban where hezuohuoban.id=#{id,jdbcType=INTEGER}")
    HezuohuobanModel selecthzhb(int id);


    @Update(" update hezuohuoban" +
            "  set pic = #{pic,jdbcType=VARCHAR}, " +
            "  title= #{title,jdbcType=VARCHAR},"+
            "  wailian= #{wailian,jdbcType=VARCHAR}" +
            "   where hezuohuoban.id=#{id,jdbcType=INTEGER}"
    )
    void updatehzhb(String pic,String title,String wailian,int id);


    //--------------------------------------------------------------
    @Update(" update chioceanswer" +
            "  set a = #{a,jdbcType=VARCHAR}, " +
            "  b= #{b,jdbcType=VARCHAR},"+
            "  c= #{c,jdbcType=VARCHAR},"+
            "  d= #{d,jdbcType=VARCHAR},"+
            "  ans= #{ans,jdbcType=VARCHAR},"+
            "  tigan= #{tigan,jdbcType=VARCHAR}" +
            "   where chioceanswer.tikuid=#{id,jdbcType=INTEGER}"
    )
    void updatexuanze(String a,String b,String c,String d,String tigan,String ans,int id);

    @Update(" update boolanswer" +
            "  set a = #{a,jdbcType=VARCHAR}, " +
            "  b= #{b,jdbcType=VARCHAR},"+
            "  ans= #{ans,jdbcType=VARCHAR},"+
            "  tigan= #{tigan,jdbcType=VARCHAR}" +
            "   where boolanswer.tikuid=#{id,jdbcType=INTEGER}"
    )
    void updatepanduan(String a,String b,String tigan,String ans,int id);

    @Update(" update tiankonganswer" +
            "  set a = #{a,jdbcType=VARCHAR}, " +
            "  b= #{b,jdbcType=VARCHAR},"+
            "  ans= #{ans,jdbcType=VARCHAR},"+
            "  tigan= #{tigan,jdbcType=VARCHAR}" +
            "   where tiankonganswer.tikuid=#{id,jdbcType=INTEGER}"
    )
    void updatetiankong(String tigan,String ans,int id);

    @Update(" update resultfenxianswer" +
            "  set pic = #{pic,jdbcType=VARCHAR}, " +
            "  ans= #{ans,jdbcType=VARCHAR},"+
            "   where resultfenxianswer.tikuid=#{id,jdbcType=INTEGER}")
    void updatefenxi(String pic,String ans,int id);

    /**
     * 选择 +
     * @param tikuid
     * @param a
     * @param b
     * @param c
     * @param d
     * @param tigan
     * @param ans
     */
    @Insert("insert into chioceanswer(tikuid,a,b,c,d,tigan,ans)" +
            "values (#{tikuid,jdbcType=INTEGER}," +
            "#{a,jdbcType=VARCHAR}," +
            "#{b,jdbcType=VARCHAR}," +
            "#{c,jdbcType=VARCHAR}," +
            "#{d,jdbcType=VARCHAR}," +
            "#{tigan,jdbcType=VARCHAR}," +
            "#{ans,jdbcType=VARCHAR})"
    )
    void addxuanze(int tikuid,String a,String b,String c,String d,String tigan,String ans);

    /**
     * 判断
     * @param tikuid
     * @param a
     * @param b
     * @param tigan
     * @param ans
     */
    @Insert("insert into boolanswer(tikuid,a,b,tigan,ans)" +
            "values (#{tikuid,jdbcType=INTEGER}," +
            "#{a,jdbcType=VARCHAR}," +
            "#{b,jdbcType=VARCHAR}," +
            "#{tigan,jdbcType=VARCHAR}," +
            "#{ans,jdbcType=VARCHAR})"
    )
    void addpanduan(int tikuid,String a,String b,String tigan,String ans);


    /**
     * 填空
     * @param tikuid
     * @param tigan
     * @param ans
     */
    @Insert("insert into tiankonganswer(tikuid,a,b,tigan,ans)" +
            "values (#{tikuid,jdbcType=INTEGER}," +
            "#{tigan,jdbcType=VARCHAR}," +
            "#{ans,jdbcType=VARCHAR})"
    )
    void addtiankong(int tikuid,String tigan,String ans);

    /**
     * 分析
     * @param tikuid
     * @param pic
     * @param ans
     */
    @Insert("insert into resultfenxianswer(tikuid,pic,ans)" +
            "values (#{tikuid,jdbcType=INTEGER}," +
            "#{pic,jdbcType=VARCHAR}," +
            "#{ans,jdbcType=VARCHAR})"
    )
    void addfenxi(int tikuid,String pic,String ans);



    //-------------------------------------------------------
    @Select("select * from zhishi")
    List<ZhishitiaoOriModel> selectAllzhishitiao();

    /**
     * 删
     * @param
     */
    @Delete("delete from zhishi where zhishi.id=#{id,jdbcType=INTEGER}")
    void delzhishitiao(int id);

    @Insert("insert into zhishi(miaoshu,parentid,zhangjieid)" +
            "values (#{miaoshu,jdbcType=VARCHAR}," +
            "#{parentid,jdbcType=INTEGER}," +
            "#{zhangjieid,jdbcType=INTEGER})"
    )
    void addzhishitiao(String miaoshu,int parentid,int zhangjieid);

    /**
     * 查一个
     * @param id
     * @return
     */
    @Select("select * from zhishi where zhishi.id=#{id,jdbcType=INTEGER}")
    ZhishitiaoOriModel selectzhishitiao(int id);


    @Update(" update zhishi " +
            "  set miaoshu = #{miaoshu,jdbcType=VARCHAR}, " +
            "  parentid= #{parentid,jdbcType=INTEGER},"+
            "  zhangjieid= #{zhangjieid,jdbcType=INTEGER}" +
            "   where zhishi.id=#{id,jdbcType=INTEGER}"
    )
    void updatezhishitiao(String miaoshu,int parentid,int zhangjieid,int id);

    //-------------------------------------------------------
    @Select("select * from user")
    List<User> selectAlluser();

    /**
     * 删
     * @param
     */
    @Delete("delete from user where user.uid=#{id,jdbcType=INTEGER}")
    void deluser(int id);

    /**
     * 查一个
     * @param id
     * @return
     */
    @Select("select * from user where user.uid=#{id,jdbcType=INTEGER}")
    User selectuser(int id);

    /**\
     * 更改权限
     * @param auth
     * @param id
     */
    @Update(" update user " +
            "  set auth = #{auth,jdbcType=INTEGER}"+
            "   where user.uid=#{id,jdbcType=INTEGER}"
    )
    void updateuser(int auth,int id);

}
