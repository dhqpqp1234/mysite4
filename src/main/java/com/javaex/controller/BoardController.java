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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {
	
	//필드
	@Autowired
	private BoardService boardService;
	
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//리스트 
	@RequestMapping(value="/board/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model) {
		System.out.println("BoardController>list()");
		
		List<BoardVo> boardList = boardService.boardList();
		
		model.addAttribute("boardList",boardList);
		
		
		return "/board/list";
	}
	
	//등록 폼
	@RequestMapping(value = "/board/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardWriteForm() {
		System.out.println("BoardController>boardWriteForm()");
		
		return "/board/writeForm";
	}
	
	//등록
	@RequestMapping(value="/board/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardInsert(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController>boardInsert()");
		
		 boardService.boardInsert(boardVo);
		
		return "redirect:/board/list";
	}
	
	//읽기
	@RequestMapping(value="/board/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardRead(@RequestParam("no") int no, Model model) {
		System.out.println("BoardController>boardRead()");
		
		boardService.boardUphit(no);
		
		BoardVo boardVo = boardService.boardRead(no);
		
		model.addAttribute("boardVo",boardVo);
		
		
		
		return "/board/read";
	}
	
	//삭제
	@RequestMapping(value="/board/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardDelete(@RequestParam("no") int no ) {
		System.out.println("BoardController>boardDelete()");
		
		boardService.boardDelete(no);
		
		
		System.out.println(no);
		
		return "redirect:/board/list";
	}
	
	//수정폼
	@RequestMapping(value="/board/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardModifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("BoardController>modifyForm()");
			
		BoardVo boardVo = boardService.getBoardUser(no);
		
		model.addAttribute("boardVo" ,boardVo);
		
		return "/board/modifyForm";
	}
	
	//수정
	@RequestMapping(value="/board/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardModify(@ModelAttribute BoardVo boardVo, Model model) {
		System.out.println("BoardController>modify()");
		
		boardService.boardUpdate(boardVo);
		
		System.out.println(boardVo);
		
		return "redirect:/board/list";
	}
	
	
}
