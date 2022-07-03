package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//리스트
	@RequestMapping(value="gallery/list", method= {RequestMethod.POST, RequestMethod.GET})
	public String galleryList( Model model) {
		System.out.println("GalleryController>galleryList");
		
		List<GalleryVo> gList = galleryService.galleryList();
		
		model.addAttribute("gList",gList);	
		System.out.println(gList);
		return "gallery/list";
	}
	
	//파일 업로드
	@RequestMapping(value="gallery/upload", method= {RequestMethod.POST, RequestMethod.GET})
	public String galleryUpload(@RequestParam("file") MultipartFile file, Model model, GalleryVo gVo) {
		System.out.println("GalleryController>upload");
		
		String saveName = galleryService.save(file, gVo);
		
		model.addAttribute("saveName",saveName);
		
		return "redirect:/gallery/list";
	}
	//보기
	@ResponseBody
	@RequestMapping(value="gallery/read", method= {RequestMethod.POST, RequestMethod.GET})
	public GalleryVo galleryRead(@RequestBody String saveName) {
		System.out.println("GalleryController>read");
		
		GalleryVo gVo = galleryService.read(saveName);
		
		
		
		return gVo;
	}
	
	//지움
	@ResponseBody
	@RequestMapping(value="gallery/delete", method= {RequestMethod.POST, RequestMethod.GET})
	public int galleryDelete(@RequestBody int no) {
		System.out.println("GalleryController>delete");
		
		int count = galleryService.delete(no);
		
		System.out.println(count);
		return count;
	}
	
}
