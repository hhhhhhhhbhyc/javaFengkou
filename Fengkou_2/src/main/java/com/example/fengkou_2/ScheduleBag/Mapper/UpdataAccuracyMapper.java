package com.example.fengkou_2.ScheduleBag.Mapper;

import com.example.fengkou_2.LuntanBag.Model.LuntanModel;
import com.example.fengkou_2.ScheduleBag.Model.UpdateAccuracyModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UpdataAccuracyMapper {

    /**
     * 自动根据答题纸更新通过率
     */
    @Update(" update tiku" +
            " inner join(select avg(isCorrect) avgpass,tid from useranswersheet group by tid) b" +
            " on tiku.tid = b.tid set tiku.passratio = b.avgpass"
    )
    void updateselect();
}
