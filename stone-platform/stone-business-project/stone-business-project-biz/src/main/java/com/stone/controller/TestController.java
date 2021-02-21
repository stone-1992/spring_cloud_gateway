package com.stone.controller;


import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

    public String name = "张三";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.forEach(r -> {
            System.err.println(r);
        });
        Collections.shuffle(list);
        list.forEach(r -> {
            System.err.println(r);
        });
        Collections.shuffle(list);
        list.forEach(r -> {
            System.err.println(r);
        });


    }

}
