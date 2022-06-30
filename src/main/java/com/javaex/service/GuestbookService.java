package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestbookService {
	@Autowired
	GuestBookDao guestDao;
	//리스트
	public List<GuestBookVo> guestList(){
		System.out.println("GuestService>addList");
		
		List<GuestBookVo> guestList = guestDao.selectList();
		
		return guestList;
	}
	//등록
	public int guestInsert(GuestBookVo guestVo) {
		System.out.println("GuestService>guestInsert");
		
		int count = guestDao.guestInsert(guestVo);
		
		return count;
	}
	
	
	
	//1명 정보가져오기
	public GuestBookVo getGuestUser(int no) {
		System.out.println("GuestService>getGuestUser");
		
		GuestBookVo guestVo = guestDao.getGuestUser(no);
		
		return guestVo;
	}
	
	//삭제
	public int guestDelete(GuestBookVo guestVo) {
		
		int count = guestDao.guestDelete(guestVo);
		
		return count;
	}
	
	//방명록 저장 (ajax)
		public GuestBookVo addGuest(GuestBookVo gVo) {
			System.out.println("GuestService>addGuest");
			
			guestDao.InsertGuest(gVo);
			
			int no = gVo.getNo();
			
			//방금저장한 1개의 데이터를 가져온다
			GuestBookVo vo = guestDao.getGuest(no);
			
			return vo;
		}
		
		//방명록 삭제
		public String removeGuest(GuestBookVo gVo) {
			System.out.println("GuestService>removeGuest");
			
			String state;
			
			int count = guestDao.removeGuest(gVo);
			
			if(count>0) {
				state = "succeess";
			}else {
				state = "fail";
			}
			
			return state;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
