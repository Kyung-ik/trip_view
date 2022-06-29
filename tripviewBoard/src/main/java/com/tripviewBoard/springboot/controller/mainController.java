package com.tripviewBoard.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripviewBoard.springboot.model.response.TripviewBoardResponseDto;
import com.tripviewBoard.springboot.service.TripviewBoardService;

import lombok.AllArgsConstructor;


@RequestMapping("/board")
@Controller
@AllArgsConstructor
public class mainController {
	
	private TripviewBoardService boardService;

	
	@GetMapping("/index")
	public String home() {
		return "board/index";
	}
	
	//게시글 목록
	@GetMapping("/")
	public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
		List<TripviewBoardResponseDto> boardList = boardService.getBoardlist(pageNum);
		Integer[] pageList = boardService.getPageList(pageNum);
		
		model.addAttribute("boards",pageNum);
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageList", pageList);
		return "board/searchList";
	}
	
	
	
	
	@GetMapping("searchList")	//검색 페이지로 이동
	public String search(@RequestParam(value="keyword") String keyword,Model model) {
		List<TripviewBoardResponseDto> boardDtoList = boardService.searchPosts(keyword);
		model.addAttribute("boardList", boardDtoList);
		
		return "board/searchList";
	}
	
}
