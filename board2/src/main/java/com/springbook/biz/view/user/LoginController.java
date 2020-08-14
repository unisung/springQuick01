package com.springbook.biz.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.impl.UserDAO;

public class LoginController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
         System.out.println("로그인 처리");
         
         //1. 사용자 입력 정보 추출
         String id=request.getParameter("id");
         String password=request.getParameter("password");
         
         ModelAndView mav = new ModelAndView();
       //2.db연동처리
         UserVO vo=new UserVO();
         vo.setId(id);
         vo.setPassword(password);
         
         UserDAO userDAO = new UserDAO();
         UserVO user = userDAO.getUser(vo);
         
        HttpSession session =request.getSession(); 
         
     //3. 화면 네비게이션
         if(user!=null) {
        	 session.setAttribute("user",user);
        	 mav.setViewName("redirect:getBoardList.do"); 
         }else {
        	 mav.setViewName("redirect:login.jsp");
         }
		return mav;
	}
	
}
