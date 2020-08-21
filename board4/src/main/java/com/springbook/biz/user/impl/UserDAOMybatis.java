package com.springbook.biz.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

 //회원가입
public void register(UserVO vo) {
	System.out.println("===> Mybatis로 registerUser() 기능 처리");
    mybatis.insert("UserDAO.registerUser", vo);
}

public Map<String, String> getRoles() {
	System.out.println("===> Mybatis로 getRoles() 기능 처리");
	Map<String, String> map= new HashMap<String, String>();
	List<RoleVO> list = mybatis.selectList("UserDAO.getRoles");
	for(int i=0;i<list.size();i++) {
		map.compute(i,list.get(i));
	}
	return map;
}
 
	
}
