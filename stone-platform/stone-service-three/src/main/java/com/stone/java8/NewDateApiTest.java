package com.stone.java8;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java8 新的时间日期 api
 */
public class NewDateApiTest {


    /**
     * 1、LocalDate  LocalTime  LocalDateTime
     */
    @Test
    public void test1(){
        // 1、获取实例
        LocalDateTime ldt = LocalDateTime.now();
        System.err.println("ldt : " + ldt);

        // 2、当前系统时间加2天
        LocalDateTime localDateTime = ldt.plusDays(2);
        System.err.println("localDateTime : " + localDateTime);

        // 3、减两个月
        LocalDateTime minusMonth = ldt.minusMonths(2);
        System.err.println("minusMonth : " + minusMonth);

        // 4、获取年月日时
        System.err.println(ldt.getYear());
        System.err.println(ldt.getMonthValue());
        System.err.println(ldt.getDayOfMonth());

        // 5、时间戳 1970年1月1日 00:00:00 到某个时间之间的毫秒值
        Instant instant = Instant.now();
        System.err.println("instant : " + instant);

        // 6、加八个小时
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.err.println("offsetDateTime : " + offsetDateTime);

        // 7、两个时间间间隔
        System.err.println(Duration.between(Instant.now(), Instant.now()).toNanos());




    }


}
