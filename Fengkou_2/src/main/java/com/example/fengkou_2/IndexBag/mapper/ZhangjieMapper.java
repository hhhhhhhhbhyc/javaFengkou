package com.example.fengkou_2.IndexBag.mapper;

import com.example.fengkou_2.IndexBag.Model.Zhangjie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZhangjieMapper {
    @Select("select * from zhangjie order by zhang,jie")
    List<Zhangjie> selectallFromZhangjie();
}
