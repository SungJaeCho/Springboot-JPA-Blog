package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data //getter+setter
//@AllArgsConstructor
@NoArgsConstructor //빈생성자
//@RequiredArgsConstructor //fianl 붙은 애들 생성자 만들어줌
public class Member {
	//변수의 상태는 항상 메소드에 의해서 변경되게 만들어야함.
	private  int id; //데이터가 변경되지 않게 FINAL로 잡아둠 DB에서 가져온 값이 변경할 일이없어서
	private  String username;
	private  String password;
	private  String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}	
	
	
}
