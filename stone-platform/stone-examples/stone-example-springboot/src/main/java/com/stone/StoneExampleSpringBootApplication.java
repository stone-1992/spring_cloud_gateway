package com.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * SpringBootApplication : 来标注一个主程序类，说明这是一个 Spring Boot 应用
 */
@SpringBootApplication
public class StoneExampleSpringBootApplication {

    public static void main(String[] args) {

        Map map = new HashMap();

        // Spring 应用启动起来
        SpringApplication.run(StoneExampleSpringBootApplication.class, args);
    }

}
