package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//리스트
	public List<BoardVo> boardList(){
		System.out.println("BoardDao>boardList()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.boardList");
		
		System.out.println(boardList);
		
		return boardList;
		
	}
	
	//등록
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardDao>boardInsert()");
		
		int count = sqlSession.insert("board.boardInsert",boardVo);
		return count;
	}
	
	//삭제
	public int boardDelete(int no) {
		System.out.println("BoardDao>boardDelete()");
		
		int count = sqlSession.delete("board.boardDelete",no);
		
		return count;
	}
	
	//수정폼(정보가져오기)
	public BoardVo getBoardUser(int no) {
		System.out.println("BoardDao>boardGetUser()");
		
		BoardVo boardVo = sqlSession.selectOne("board.boardGetUser",no);
		
		System.out.println(boardVo	);
		
		return boardVo;
	}
	
	//수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("BoardDao>boardUpdate()");
		
		int count = sqlSession.update("board.boardUpdate",boardVo);
		
		return count;
	}
	
	//읽기
	public BoardVo boardRead(int no) {
		System.out.println("BoardDao>boardRead()");
		
	 	BoardVo boardVo = sqlSession.selectOne("board.boardGetUser", no);
	 	
	 	
	 	return boardVo;
	}
	
}
