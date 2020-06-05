package com.stone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class UserController {
	
	@RequestMapping("getone")
	public String getUser() {
		
		return "this is person : service-two - getone";
	}
}
