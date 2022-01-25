package com.example.fengkou_2.ScheduleBag.Service;

import com.example.fengkou_2.ScheduleBag.Mapper.UpdataAccuracyMapper;
import com.example.fengkou_2.ScheduleBag.Model.UpdateAccuracyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccurateService {
    @Autowired
    private UpdataAccuracyMapper updataAccuracyMapper;
    /**
     * 定时任务，1分钟更新一次正确率
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateaccuracy(){
        //System.out.println("update");
        updataAccuracyMapper.updateselect();
    }
}
