package com.stone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Value(value = "${server.port}")
    private String serverPort;

    @GetMapping(value = "getOne")
    public String getTest(){
        return "this is person :　" + serverPort;
    }

}
