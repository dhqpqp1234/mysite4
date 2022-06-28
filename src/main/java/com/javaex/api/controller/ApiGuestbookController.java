package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestBookVo;
@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//방명록 첫페이지 (등록폼 + 리스트(ajax))
	@RequestMapping(value="/api/guestbook/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestbookController>addList()");
		
		return "apiGuestbook/addList";
	}
	
	
	//방명록 리스트 데이터만 보내줭
	@ResponseBody //데이터를 body에 보내랑
	@RequestMapping(value="/api/guestbook/list", method= {RequestMethod.POST, RequestMethod.GET})
	public List<GuestBookVo> list() {
		System.out.println("ApiGuestbookController>list()");
		
		List<GuestBookVo> guestList = guestbookService.guestList();
		System.out.println(guestList);
		
		return guestList;
	}
	
	//방명록에 저장
	@ResponseBody
	@RequestMapping(value="/api/guestbook/add", method= {RequestMethod.POST,RequestMethod.GET})
	public GuestBookVo add(@ModelAttribute GuestBookVo gVo) {
		System.out.println("ApiGuestbookController>add()");
		
		 GuestBookVo vo = guestbookService.addGuest(gVo);
		 System.out.println(vo);
		 
		return gVo;
	}
	
}