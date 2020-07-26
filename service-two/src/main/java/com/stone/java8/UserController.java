package com.stone.java8;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("who")
	public String getUser() {
		
		return "this is user : service-two";
	}
}
