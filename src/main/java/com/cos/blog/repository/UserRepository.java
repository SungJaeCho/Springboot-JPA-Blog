package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean등록 됨
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
	// Select * from user where username = 1?;
	Optional<User> findByUsername(String username);
	
	
}


// JPA Naming쿼리
// select * from user where username = 1 and password = 2;
//User findByUsernameAndPassword(String username, String password);

//@Query(value = " select * from user where username = 1 and password = 2", nativeQuery = true)
//User login(String username, String password);