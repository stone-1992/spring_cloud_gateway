package com.stone.java8;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@RequestMapping("/hello")
	public String login() {
		return "hello login eureka-server";
	}
}
