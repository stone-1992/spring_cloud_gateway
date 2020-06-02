package com.stone.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@RequestMapping("who")
	public String getUser() {
		
		return "this is user : service-one1";
	}
	
	
}
