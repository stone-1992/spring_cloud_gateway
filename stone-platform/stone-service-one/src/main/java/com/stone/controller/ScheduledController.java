package com.stone.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.Api;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务
 * @author 周泽星
 *
 */
@Api(tags = "定时任务")
@RestController
@Component
public class ScheduledController {
	/**
	 * 测试定时任务        6秒执行一次，这个方法
	 * @Scheduled(cron = "0 40 15 * * ?") 每天15:40触发
	 * @Scheduled(fixedRate = 60 * 1000)  每60秒执行一次
	 */
	@Scheduled(fixedRate = 60 * 1000)
	@PostMapping(value = "/scheduledDemo")
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
