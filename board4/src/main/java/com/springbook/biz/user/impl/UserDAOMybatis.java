package com.springbook.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAOMybatis {
 @Autowired
	private SqlSessionTemplate mybatis;
 
 //회원정보 조회
 public UserVO getUser(UserVO vo) {
	 System.out.println("===> Mybatis로 getUser() 기능 처리");
	// UserVO user = new UserVO();
	 //user =(UserVO)mybatis.selectOne("UserDAO.getUser", vo);
	 //return user;
	 return mybatis.selectOne("UserDAO.getUser", vo);
 }
 
	
}
