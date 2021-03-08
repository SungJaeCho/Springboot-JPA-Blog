package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
//	@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping(value = {"", "/"})
	public String index() { //컨트롤러에서 세션을 어떻게 찾는지?
		return "index";
	}
	
	// USER권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}
