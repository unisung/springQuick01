package com.springbook.view.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.BoardVO;
import com.springbook.biz.board.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="insertBoard.do")
	public String insertBoard(BoardVO vo) throws IOException{
		boardService.insertBoard(vo);
		return "getBoardList.do";
	}
	
	//검색 조건 목록 설정
	//@ModelAttribute("속성명") 리턴값은 객체로 리턴
	// view페이지로 전달시 Model객체에 저장됨.
	@ModelAttribute("conditionMap")
	public Map<String,String> searchConditionMap(){
		Map<String,String> conditionMap =new HashMap<>();
		conditionMap.put("전체",null);
		conditionMap.put("제목","TITLE");
		conditionMap.put("내용","CONTENT");
		return conditionMap;
	}
	
	//글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {//Model:view 로 객체 전달.
	   if(vo.getSearchCondition()==null) vo.setSearchCondition("TITLE");
	   if(vo.getSearchKeyword()==null) vo.setSearchKeyword("");
		
		model.addAttribute("boardList", boardService.getBoardList(vo));
		return "getBoardList.jsp";
	}
	
	//글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model/* ,HttpServletRequest request */) {
		System.out.println("글번호:"+vo.getSeq());
		//int seq=Integer.parseInt(request.getParameter("seq"));
		model.addAttribute("board", boardService.getBoard(vo));//Model에 board정보 저장
		return "getBoard.jsp";//View명 리턴
	}

}
