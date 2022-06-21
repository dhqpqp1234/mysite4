package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	SqlSession sqlSession;
	//리스트
	public List<GuestBookVo> selectList(){
	
	System.out.println("GuestDao>selectList()");	
		
	List<GuestBookVo> guestList = sqlSession.selectList("guestbook.addList");
	
	return guestList;
	}
	//등록
	public int guestInsert(GuestBookVo guestVo) {
		System.out.println("GuestDao>guestInsert");
		
		int count = sqlSession.insert("guestbook.guestInsert", guestVo);
		
		return count;
	}

	//1명 정보 가져오기
	public GuestBookVo getGuestUser(int no) {
		System.out.println("GuestDao>getGuestUser");
		
		GuestBookVo guestUser = sqlSession.selectOne("guestbook.guestSelect", no);
		
		return guestUser;
	}
	
	//삭제
	public int guestDelete(GuestBookVo guestVo) {
		System.out.println("GuestDao>guestDelete");
		
		int count = sqlSession.delete("guestbook.guestDelete", guestVo);
		
		return count;
	}
	
}
