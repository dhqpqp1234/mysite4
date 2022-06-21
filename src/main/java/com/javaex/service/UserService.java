package com.javaex.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	//필드
	@Autowired
	private UserDao userDao;
	//생성자 -- 디폴트 생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("UserService>join()");
		//회원가입 비지니스 로직
		//다오를 통해서 데이터 저장
		
		 int count = userDao.userInsert(userVo);
		 
		 return count;
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("UserService>login()");
		
		UserVo authUser = userDao.getUser(userVo);
		
		System.out.println("Service" + authUser);
		
		return authUser;
	}
	
	//회원 정보 가져오기
	public UserVo getModifyUser(int no) {
		System.out.println("UserService>getUserModify()");
		
		UserVo userVo = userDao.getModifyUser(no);
		return userVo;
	}
	
	//수정
	public int userUpdate(UserVo userVo) {
		
		int count = userDao.userUpdate(userVo);
		
		return count;
		
	}
	
}
