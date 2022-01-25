package com.example.fengkou_2.EchartsBag.Mapper;

import com.example.fengkou_2.TiKuBag.Model.TikuModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PersonalRatioaMapper {
    @Select("select isCorrect from useranswersheet")
    List<Object> selectAlldata();
}
