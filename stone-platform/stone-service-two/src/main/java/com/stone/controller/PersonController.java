package com.stone.controller;

import com.stone.core.util.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stone
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Value(value = "${server.port}")
    private String serverPort;

    @GetMapping(value = "getOne")
    public R getTest() {
        return R.ok("this is person :　" + serverPort);
    }

}
