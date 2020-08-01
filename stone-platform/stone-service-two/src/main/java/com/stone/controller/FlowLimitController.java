package com.stone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel s流控 Controller
 */
@RestController
public class FlowLimitController {
    @GetMapping(value = "testA")
    public String testA() {

        return "this is testA ---------------";
    }

    @GetMapping(value = "testB")
    public String testB() {

        return "this is testB ---------------";
    }
}
