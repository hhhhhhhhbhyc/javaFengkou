package com.example.fengkou_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableCaching //缓存
@EnableAsync//异步
@EnableScheduling//定时任务
@SpringBootApplication
public class Fengkou2Application {

    public static void main(String[] args) {
        SpringApplication.run(Fengkou2Application.class, args);
    }

}
