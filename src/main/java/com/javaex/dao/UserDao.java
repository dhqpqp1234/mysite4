package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;
@Repository
public class UserDao {
	
	//필드
	@Autowired
	private SqlSession sqlSession;
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//회원정보 가져오기(로그인)
	public UserVo getUser(UserVo userVo) {
		System.out.println("UserDao>getUser()");
		
		UserVo authUser = sqlSession.selectOne("user.getUser", userVo);
		
		
		return authUser;
	}
	
	//회원정보저장
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao>userInsert()");
		
		int count =  sqlSession.insert("user.userInsert", userVo);
		
		return count;
	}
	
	//회원 정보 
	public UserVo getModifyUser(int no) {
		System.out.println("UserDao>userGetUser");
		
		
		UserVo getUSerOne = sqlSession.selectOne("user.getModifyUser",no);
		
		return getUSerOne;
	}
	
	//회원정보 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao>userUpdate");
		
		int count = sqlSession.update("user.userUpdate", userVo);
		
		return count;
	}
	
}
