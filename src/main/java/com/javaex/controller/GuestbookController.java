package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestbookController {
	@Autowired
	GuestbookService guestService; 
	//리스트
	@RequestMapping(value="/guestbook/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController>addList");
		
		List<GuestBookVo> guestList =  guestService.guestList();
		
		model.addAttribute("guestList", guestList);
		return "/guestbook/addList";
	}
	
	//추가
	@RequestMapping(value="/guestbook/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestBookVo guestVo) {
		System.out.println("guestbook>add");
		
		guestService.guestInsert(guestVo);
		
		 System.out.println(guestVo);
		return "redirect:/guestbook/addList";
	}
	
	//삭제폼
	@RequestMapping(value="/guestbook/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteFrom(@RequestParam("no") int no, Model model) {
		System.out.println("guestbook>deleteForm");
		
		System.out.println(no);
		GuestBookVo guestVo = guestService.getGuestUser(no);
		
		model.addAttribute("guestUser",guestVo);
		
		return "/guestbook/deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/guestbook/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestBookVo guestVo) {
		System.out.println("guestbook>delete");
		
		guestService.guestDelete(guestVo);
		
		return "redirect:/guestbook/addList";
	}
}
