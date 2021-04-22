package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Modifying //int Integer만 리턴가능
	@Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())", nativeQuery = true)
	int mSave(int userId, int boardId, String content);

}
