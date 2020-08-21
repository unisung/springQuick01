package com.springbook.biz.user;

import java.util.Map;

public interface UserService {
	public UserVO getUser(UserVO vo);
	public void register(UserVO vo);
	public Map<String, String> getRoles();

}
