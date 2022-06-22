package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	//필드
	@Autowired
	private BoardDao boardDao;
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//리스트
	public List<BoardVo> boardList(){
		System.out.println("BoardService>boardList()");
		
		List<BoardVo> boardList = boardDao.boardList();
		
		return boardList;
	}
	
	//등록
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardService>boardInsert()");
		
		int count = boardDao.boardInsert(boardVo);
		
		return count;
	}
	
	//삭제
	public int boardDelete(int no) {
		System.out.println("BoardService>boardDelete()");
		
		int count = boardDao.boardDelete(no);
		
		return count;
	}
	
	//회원정보 가져오기
	
	
	//읽기
	 
}
