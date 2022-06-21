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
		
		return boardList;
		
	}
	
	//등록
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardDao>boardInsert()");
		
		int count = sqlSession.insert("board.boardInsert",boardVo);
		return count;
	}
	
	//삭제
	public int boardDelete(BoardVo boardVo) {
		System.out.println("BoardDao>boardDelete()");
		
		int count = sqlSession.delete("board.boardDelete",boardVo);
		
		return count;
	}
	
}
