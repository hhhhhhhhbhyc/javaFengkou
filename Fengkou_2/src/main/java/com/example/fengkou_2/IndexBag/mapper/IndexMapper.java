package com.example.fengkou_2.IndexBag.mapper;

import com.example.fengkou_2.IndexBag.Model.HezuohuobanModel;
import com.example.fengkou_2.IndexBag.Model.WebquestionModel;
import com.example.fengkou_2.IndexBag.Model.Zhangjie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IndexMapper {
    @Select("select * from hezuohuoban")
    List<HezuohuobanModel> selecthezuohuoban();


    @Select("select * from webQuestion order by quan desc")
    List<WebquestionModel> selecwebQ();
}
