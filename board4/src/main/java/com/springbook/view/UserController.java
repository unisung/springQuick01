package com.springbook.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
   
	@RequestMapping(value="/register.do",method = RequestMethod.GET)
	public String register() {
		return "register.jsp";
	}
	
	//회원가입 처리 후 로그인 창으로 이동
	@RequestMapping(value="/register.do",method = RequestMethod.POST)
	public String registerPro(UserVO vo) {
		System.out.println(vo);
		//db저장
		vo.setRole("user");//admin,user
		userService.register(vo);
		
		return "redirect:login.do";
	}
}
