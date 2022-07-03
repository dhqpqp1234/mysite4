package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	//리스트
	public List<GalleryVo> galleryList() {
		System.out.println("GalleryService>galleryList");
		
		
		List<GalleryVo> gList = galleryDao.selectList();
		
		return gList;
	}
	
	//파일 업로드
	public String save(MultipartFile file, GalleryVo gVo) {
		System.out.println("GalleryService>upload");
		
		String saveDir ="C:\\javaStudy\\upload";
	      
	      //오리지널 파일이름
	      String orgName = file.getOriginalFilename();
	      
	      //.단위로 잘라주기
	      String exName = orgName.substring(orgName.lastIndexOf("."));
	      
	      //파일이름이 겹칠수 없게한다.
	      String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
	      
	      //파일경로
	      String filePath = saveDir + "\\" + saveName;
	      
	      long fileSize = file.getSize();
	      
	      gVo.setOrgName(orgName);	      
	      gVo.setSaveName(saveName);
	      gVo.setFileSize(fileSize);
	      gVo.setFilePath(filePath);
	      
	      galleryDao.save(gVo);
	      
	      //파일 정보(DB저장) 추출 저장
	            try {
	               byte[] fileData = file.getBytes();
	               
	               OutputStream os = new FileOutputStream(filePath);
	               BufferedOutputStream bos = new BufferedOutputStream(os);
	               
	               bos.write(fileData); //저장
	               bos.close();
	               
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
				
				return saveName;
				
			}
	
	//사진가져오기
	public GalleryVo read(String saveName) {
		System.out.println("GalleryService>read");
		
		GalleryVo Gvo = galleryDao.read(saveName);
		
		return Gvo;
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("service>delete");
		
		int count = galleryDao.delete(no);
		
		return count;
	}
	
}
