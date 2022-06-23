package com.tripviewBoard.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tripviewBoard.springboot.service.CommentService;
import com.tripviewBoard.springboot.service.LikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class LikeController {

	
	private final LikeService likeService;
	
	
	//좋아요
    @PostMapping("/like/{boardId}")
    public @ResponseBody int like(Long boardId, Long memberId) {
        int result = likeService.saveLike(boardId,memberId);
        return result;
    }

}
