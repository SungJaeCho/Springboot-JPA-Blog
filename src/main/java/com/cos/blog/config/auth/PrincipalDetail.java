package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가되면 UserDetails타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해줌.
public class PrincipalDetail implements UserDetails{
	private User user; //객체를 품고있는것 컴포지션
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴 (true:만료되지 않음)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않았는지 리턴 (true:잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴 (true:만료되지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화인지 리턴 (true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정의 권한목록을 리턴 ( 권한이 여러개 있을 수 있어서 루프를 돌아야하는데 우린 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//				return "ROLE_" + user.getRole(); //ROLE_ 이건 스프링 규칙 ROLE_USER 이런식
//			}
//		});
		
		//위에껄 람다식
		collectors.add(()->{return "ROLE_" +user.getRole();});
		
		return collectors;
	}
	
	
}
