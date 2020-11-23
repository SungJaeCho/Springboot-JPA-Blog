package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping(value = "/user/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping(value = "/user/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
}
