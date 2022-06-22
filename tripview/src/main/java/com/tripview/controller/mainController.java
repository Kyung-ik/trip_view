package com.tripview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripview.domain.repository.BoardRepository;
import com.tripview.dto.BoardDto;
import com.tripview.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("/home")
@Controller
@AllArgsConstructor
@Slf4j
public class mainController {
	
	private BoardService boardService;

	
	@GetMapping("/index")
	public String home() {
		return "home/index";
	}
	
	//게시글 목록
	@GetMapping("/")
	public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
		List<BoardDto> boardList = boardService.getBoardlist(pageNum);
		Integer[] pageList = boardService.getPageList(pageNum);
		
		model.addAttribute("board2",pageNum);
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageList", pageList);
		return "home/searchList";
	}
	
	
	@GetMapping("/post")
	public String write() {
		return "home/write";
	}
	
	@PostMapping("/post/searchList")
	public String write(BoardDto boardDto) {
		log.info("params={}",boardDto);
		
		boardService.savePost(boardDto);
		
		return "redirect:/";
	}
	
	@GetMapping("searchList")
	public String search(@RequestParam(value="keyword") String keyword,Model model) {
		List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
		model.addAttribute("boardList", boardDtoList);
		
		return "home/searchList";
	}
	
	@GetMapping("post/{no}")
	public String detail(@PathVariable("no")Long no, Model model) {
		BoardDto boardDTO = boardService.getPost(no);
		
		model.addAttribute("boardDto",boardDTO);
		return "detail";
	}
	
	@GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "home/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }
	
	
}
