package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.BoardVO;
import com.springbook.biz.board.impl.BoardDAOSpring2.BoardRowMapper;
import com.springbook.biz.common.JDBCUtil;

/* JDBCDaoSupport 상속 */
//@Repository
public class BoardDAOSpring2 extends JdbcDaoSupport{

	public class BoardRowMapper implements RowMapper<BoardVO> {
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BoardVO board = new BoardVO();
            board.setSeq(rs.getInt("seq"));
            board.setTitle(rs.getString("title"));
            board.setWriter(rs.getString("writer"));
            board.setContent(rs.getNString("content"));
            board.setRegDate(rs.getDate("regdate"));
            board.setCnt(rs.getInt("cnt"));
			return board;
		}
	}

	@Autowired
  public void setSupportDataSource(DataSource dataSource) {
	  super.setDataSource(dataSource);
  }

	//SQL명령어
		private final String BOARD_INSERT="insert into board(seq,title,writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
		private final String BOARD_UPDATE="update board set title=?,content=? where seq=?";
		private final String BOARD_DELETE="delete from board where seq=?";
		private final String BOARD_GET="select * from board where seq=?";
		private final String BOARD_LIST="select * from board order by seq desc";

		//글등록 메소드
		public void insertBoard(BoardVO vo) {
			System.out.println("===> jdbc로 insertBoard()기능 처리");
			getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(), vo.getWriter(),vo.getContent());
		}
		
	   //글 목록 조회
		public List<BoardVO> getBoardList(BoardVO vo) {
			System.out.println("===>JDBC로 getBoardLiist() 기능 처리");
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
		}

		//글 상세 조회
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("===>JDBC로 getBoard()기능 처리");
			Object[] args= {vo.getSeq()};
			return getJdbcTemplate().queryForObject(BOARD_GET, args,new BoardRowMapper());
		}

		//글 수정
		public void updateBoard(BoardVO vo) {
		   System.out.println("===>JDBC로 updateBoard()기능 처리");
		   getJdbcTemplate().update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
		}

		//글 삭제
		public void deleteBoard(BoardVO vo) {
		   System.out.println("===>JDBC로 deleteBoard() 기능 처리");
		   getJdbcTemplate().update(BOARD_DELETE,vo.getSeq());
		   
		}
	
}
