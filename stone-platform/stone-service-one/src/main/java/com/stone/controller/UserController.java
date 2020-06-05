package com.stone.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "获取用户信息")
@RequestMapping("/user")
@RestController
public class UserController {

	@ApiOperation(value = "查询用户信息")
	@GetMapping("getone")
	public String getUser() {
		
		return "this is user : service-one1 - getone";
	}
	
	
}
