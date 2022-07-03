package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<GalleryVo> selectList( ) {
		System.out.println("GalleryDao>selectList");
		
		List<GalleryVo> gList = sqlSession.selectList("gallery.selectList");
		
		return gList;
	}

	public int save(GalleryVo gVo) {
		
		int count = sqlSession.insert("gallery.Insert",gVo);
		
		return count;
	}
	
	//읽기
	public GalleryVo read(String saveName) {
		System.out.println("GalleryDao>read");
		
		GalleryVo Gvo = sqlSession.selectOne("gallery.oneList",saveName);
		
		return Gvo;
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("GalleryDao>delete");
		
		int count = sqlSession.delete("gallery.delete",no);
		
		return count; 
	}
}
