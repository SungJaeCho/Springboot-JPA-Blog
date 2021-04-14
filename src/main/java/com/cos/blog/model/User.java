package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> JAVA(다른언어포함) Object->테이블로 매핑해주는 기술
@Entity //User클래스가 MySql에 테이블이 생성
//@DynamicInsert // insert시 nulll 제외 좋은방법은 아님 계속 뭔가 붙이게됨
public class User {
	
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //넘버링 자동 올림 프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; //id
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'") //''들어가있음 문자로인식
	//DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다.. 도메인설정가능 (범위를 정함 3개중 한개만 쓸수있게 ex성별은 남,여 만들어가야함)//ADMIN, USER, MANAGER 
	
	@CreationTimestamp //시간이 자동으로 입력
	private Timestamp createDate;

}
