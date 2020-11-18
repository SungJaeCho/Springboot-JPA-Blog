package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입 DI
	private UserRepository userRepository;
	
	@DeleteMapping(value = "/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다. id : " + id;
	}
	
	// email. password
	@Transactional //함수종료시에 자동 COMMIT됨
	@PutMapping(value = "/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json 데이터  요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줌)
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepository.save(user); save함수는 update에선 안쓴다 아래 더티체킹을 학습 
		
		//더티 체킹
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping(value = "/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴
	@GetMapping(value = "/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
//		List<User> users = userRepository.findAll(pageable).getContent();
		
		List<User> users = pagingUser.getContent();
		return pagingUser;
	}
	
	// {id} 주소로 파라미터를 전달받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping(value = "/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// findById의 리턴값이 Optional인 이유는 리턴값이 null일수 있기때문에 Optional로 User객체를 감싸서 가져올테니 null판단은 니가 해서 리턴해라 라는 의미
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		}); //.get은 null이 절대 없다는 전제
		//람다식 표현
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다.");
//		});
		
		// 요청 : 웹브라우저(웹은 html만 이해함)
		// user 객체 : 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터 ->json
		// 스프링부트 = MessageConverter라는 애가 응답시 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서 User오브젝트를 json으로 변환해서 브라우저에게 던져줌
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 아래 세값을 가지고 요청
	@PostMapping(value = "/dummy/join")
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		user.setRole(RoleType.ADMIN);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}
