package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService { 
	@Autowired
	private FileDao fileDao;
	
	//차일 하드디스크 저장, 파일 정보(DB저장용) 추출
	public String save(MultipartFile file) {
		System.out.println("FileService>save");
		
		String saveDir ="C:\\javaStudy\\upload";
		
		
		//오리자날파일명, 저장경로(경로 + 관리하는 랜덤 파일명), 파일size(파일크기)
		//오리지날 파일이름
		String orgName = file.getOriginalFilename();
		
		//확장자
		//String exName = orgName.lastIndexOf("."); //파일이름중에 . 이있으면 몇번째에 .이 있는지 알려준다.
		String exName = orgName.substring(orgName.lastIndexOf("."));// 잘라낸다. " . "기준으로 잘라준다
		
		//저장파일명
		//2가지를 합쳐서 겹칠수 없게 한다.
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
	
		
		
		//random으로 파일명을줌 return타입이 String이 아니라 toString으로 확인
		//String test = UUID.randomUUID().toString();
		
		//long test2 = System.currentTimeMillis(); // randomUUID보다 더 엄격 1초만 지나도 숫자가 바뀜
		
		//파일경로(디렉초리+저장파일명)
		String filePath = saveDir + "\\" + saveName;
		
		//파일사이즈
		long fileSize = file.getSize();
		
		//Vo로 묶기
		FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fileVo);
		
		//Db저장 --> 과제
		fileDao.save(fileVo);
		
		
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
	
}
