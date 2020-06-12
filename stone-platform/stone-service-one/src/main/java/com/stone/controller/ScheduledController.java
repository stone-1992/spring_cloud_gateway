package com.stone.controller;

import io.swagger.annotations.Api;
import model.exption.BusinessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = "定时任务")
@RestController
@Component
public class ScheduledController {

    public int result = 0;

    /**
     * 测试定时任务        6秒执行一次，这个方法
     *
     * @Scheduled(cron = "0 40 15 * * ?") 每天15:40触发
     * @Scheduled(fixedRate = 60 * 1000)  每60秒执行一次
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void testTasks() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = null;
        try {
            str = simpleDateFormat.format(new Date()).toString();
            System.out.println("定时任务执行时间：" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
