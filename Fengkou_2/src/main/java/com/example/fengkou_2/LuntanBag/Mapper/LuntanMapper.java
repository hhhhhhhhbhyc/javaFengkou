package com.example.fengkou_2.LuntanBag.Mapper;

import com.example.fengkou_2.LuntanBag.Model.LuntanModel;
import com.example.fengkou_2.TiKuBag.Model.TikuModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface LuntanMapper {
    /**
     * 查询论坛
     *
     * @param Tikuid
     * @return
     */
    @Select("select luntan.likes,luntan.id,luntan.tikuid,luntan.username,luntan.somecontext,luntan.dealTime," +
            "user.pic from luntan,user where luntan.username=user.username and Tikuid=#{Tikuid,jdbcType=INTEGER} order by likes desc")
    List<LuntanModel> selectallfromluntan(int Tikuid);

    /**
     * 生成评论
     *
     * @param Tikuid
     * @param username
     * @param somecontext
     * @param dealDate
     */
    @Insert("insert into luntan (Tikuid,username,somecontext,dealTime)" +
            "values ( #{Tikuid,jdbcType=INTEGER}, " +
            "#{username,jdbcType=VARCHAR}, " +
            "#{somecontext,jdbcType=VARCHAR}, " +
            "#{dealDate,jdbcType=DATE}" +
            ")")
    void insert(int Tikuid, String username, String somecontext, Date dealDate);

    /**
     * 论坛点赞
     *
     * @param lid
     */
    @Update(" update luntan" +
            "   set likes = likes+1 " +
            " where id = #{lid,jdbcType=INTEGER} "
    )
    void addLikes(int lid);

    @Select("select username from luntan where id = #{lid,jdbcType=INTEGER}")
    String selectAuther(int lid);
}
