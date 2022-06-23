package com.javaex.controller;

import java.util.List;

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
@RequestMapping(value="/board")
public class BoardController {
	
	//필드
	@Autowired
	private BoardService boardService;
	
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//리스트 
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model) {
		System.out.println("BoardController>list()");
		
		List<BoardVo> boardList = boardService.boardList();
		
		model.addAttribute("boardList",boardList);
		
		
		return "board/list";
	}
	
	//검색
	@RequestMapping(value="/search", method= {RequestMethod.GET, RequestMethod.POST})
	public String search(@RequestParam("keyword") String keyword, Model model){
		System.out.println("BoardDao>search()");
		System.out.println(keyword);
		
		List<BoardVo> boardList = boardService.boardList2(keyword);
		model.addAttribute("boardList",boardList);
		
		return "board/list2";
	}
	
	//리스트 + 검색
	@RequestMapping(value="/list3", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardList3(Model model,
							@RequestParam(value="keyword", required = false, defaultValue ="") String keyword) {
		
		System.out.println("BoardController>list3()");
		System.out.println("Dao keyword" + keyword);
		
		
		List<BoardVo> boardList = boardService.boardList3(keyword);
		model.addAttribute("boardList",boardList);
		
		return "board/list3";
	}
	
	//등록 폼
	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardWriteForm() {
		System.out.println("BoardController>boardWriteForm()");
		
		return "board/writeForm";
	}
	
	//등록
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardInsert(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController>boardInsert()");
		
		 boardService.boardInsert(boardVo);
		
		return "redirect:/board/list";
	}
	
	//읽기
	@RequestMapping(value="/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardRead(@RequestParam("no") int no, Model model) {
		System.out.println("BoardController>boardRead()");
		
		boardService.boardUphit(no);
		
		BoardVo boardVo = boardService.boardRead(no);
		
		model.addAttribute("boardVo",boardVo);
		
		
		
		return "board/read";
	}
	
	//삭제
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardDelete(@RequestParam("no") int no ) {
		System.out.println("BoardController>boardDelete()");
		
		boardService.boardDelete(no);
		
		
		System.out.println(no);
		
		return "redirect:/board/list";
	}
	
	//수정폼
	@RequestMapping(value="/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardModifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("BoardController>modifyForm()");
			
		BoardVo boardVo = boardService.getBoardUser(no);
		
		model.addAttribute("boardVo" ,boardVo);
		
		return "board/modifyForm";
	}
	
	//수정
	@RequestMapping(value="/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardModify(@ModelAttribute BoardVo boardVo, Model model) {
		System.out.println("BoardController>modify()");
		
		boardService.boardUpdate(boardVo);
		
		System.out.println(boardVo);
		
		return "redirect:/board/list";
	}
	
	
}
