package com.stone.controller;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public String name = "张三";

    public static void main(String[] args) {
        System.err.println(1 << 4);
    }

}
