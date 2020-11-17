package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temp/home");
		//파일 리턴 기본경로 : src/main/resources/static
		// 리턴명 : /home.html
		return "/home.html";
	}
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/img")
	public String tempImg() {
		System.out.println("temp/home");
		//파일 리턴 기본경로 : src/main/resources/static
		// 리턴명 : /home.html
		return "/a.png";
	}
	
	// http://localhost:8000/blog/temp/jsp
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//static폴더는 컴파일이 필요없는 파일들만 jsp는 컴파일이 필요한 파일이라 webapp/WEB-INF/
		//apllication.yml에서 prefix, suffix세팅을 해주면 jsp파일을 읽음
		return "test";
	}

}
