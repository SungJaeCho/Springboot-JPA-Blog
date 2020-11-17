package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;

	@Lob//대용량 데이터
	private String content; //섬머노트 html태그가 섞여서 디자인됨 용량커짐
	
	@ColumnDefault("0") //홑따옴표 안씀
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //many=board, one=user, EAGER 무조건 들고옴
	@JoinColumn(name = "userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. FK 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //연관관계의 주인이 아니다 (FK가아님) DB에 컬럼생성 X
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
