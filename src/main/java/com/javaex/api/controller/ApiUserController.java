package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="api/user/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm(@RequestParam("id") String id, Model model) {
		System.out.println("ApiUserController>joinForm");
		
		
		
		return "apiUser/joinForm";
	}
	
}
