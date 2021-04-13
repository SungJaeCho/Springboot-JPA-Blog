package com.cos.blog.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//인증이 안된 사용자들이 출입할수 있는 경로를 /auth/**허용
//그냥 주소가 /이면 index.jsp 허용
// static이하에 있는 /js/**, /css/**, /image/** 허용
@Controller
public class UserController {

	@GetMapping(value = "/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping(value = "/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping(value = "/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) { //Data를 리턴해주는 컨트롤러 함수
		
		//POST방식으로 key=value 데이터를 요청(카카오쪽)
		RestTemplate rt = new RestTemplate(); 
		
		// HttpHeader 오브젝트 생성
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "9a29bc29db8c51f5ee3b7eb3a1d63660");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, header);
		
		// Http요청하기 - Post방식으로 - 그리고 response 변수의 응답받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(oauthToken.getAccess_token());
		
		//엑세스 토큰으로 요청
		RestTemplate rt2 = new RestTemplate(); 
		
		// HttpHeader 오브젝트 생성
		HttpHeaders header2 = new HttpHeaders();
		header2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		header2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(header2);
		
		// Http요청하기 - Post방식으로 - 그리고 response 변수의 응답받음.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
		);
		
		return response.getBody();
	}
	
	@GetMapping(value = "/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
}
