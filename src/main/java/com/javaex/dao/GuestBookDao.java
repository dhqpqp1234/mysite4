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
	
	//방명록 저장 ajax
			public int InsertGuest(GuestBookVo gVo) {
				System.out.println("GuestDao>InsertGuest");
				
				//System.out.println("쿼리 전 --->" + gVo); //no값이 없다
				
				int count = sqlSession.insert("guestbook.insertSelectKey", gVo);
				
				//System.out.println("쿼리 후 --->" + gVo); //no값이 생긴다.
				
				return count;
			}
			
			//방명록 저장후 등록한 데이터 가져오기
			public GuestBookVo getGuest(int no) {
				System.out.println("GuestDao>getGuest");
				
				GuestBookVo guestbookVo = sqlSession.selectOne("guestbook.getGuest",no);
				return guestbookVo;
			}
		
		//방명록 삭제
		public int removeGuest(GuestBookVo guestVo) {
			System.out.println("GuestDao>guestDelete");
			
			int count = sqlSession.delete("guestbook.guestDelete", guestVo);
			
			return count;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
