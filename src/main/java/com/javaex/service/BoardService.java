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
	
	//검색 리스트
	public List<BoardVo> boardList2(String keyword){
		System.out.println("BoardService>boardList2()");
		
		List<BoardVo> boardList = boardDao.boardList2(keyword);
		
		return boardList;
	}
	
	//리스트 + 검색
		public List<BoardVo> boardList3(String keyword){
			System.out.println("BoardService>boardList()");
			
			List<BoardVo> boardList = boardDao.boardList3(keyword);
			
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
	
	
	//읽기
	public BoardVo boardRead(int no) { 
		System.out.println("BoardService>boardRead()");
		
		BoardVo boardVo = boardDao.boardRead(no);
		
		return boardVo;
	}
	
	//수정폼(정보가져오기)
	public BoardVo getBoardUser(int no) {
		System.out.println("BoardService>boardGetUser()");
		
		BoardVo boardVo = boardDao.getBoardUser(no);
		
		return boardVo;
	}
	
	//수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("BoardService>boardUpdate()");
		
		int count = boardDao.boardUpdate(boardVo);
		
		return count;
	}
	
	//조회수 증가
	public int boardUphit(int no) {
		System.out.println("BoardService>boardUphit()");
		
		int count = boardDao.boardUphit(no);
		
		return count;
	}
}
