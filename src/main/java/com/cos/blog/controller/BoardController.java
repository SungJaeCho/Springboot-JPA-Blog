package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService; 
	
	//컨트롤러에서 세션을 어떻게 찾는지?
//	@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping(value = {"", "/"})
	public String index(Model model) {
		model.addAttribute("boards", boardService.글목록());
		return "index";
	}
	
	// USER권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}
