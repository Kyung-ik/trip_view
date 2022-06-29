package com.tripviewBoard.springboot.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.TripviewLike;
import com.tripviewBoard.springboot.service.CommentService;
import com.tripviewBoard.springboot.service.LikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class LikeController {

	
	private final LikeService likeService;
	
//	//	    public int saveLike(Long boardId, Long memberId) {
//	
//    Optional<TripviewLike> findLike = likeRepository.findBoard_IdAndMember_Id(boardId, memberId);
//
//
//    if (findLike.isEmpty()){	//4 //저장된 row가 없다면 board와 member를 받아 tripviewLike의 추가하는 메소드로 이동
//    	TripviewBoard board = boardRepository.findById(boardId).get();
//        Member member = memberRepository.findById(memberId).get();
//        
//        TripviewLike like = TripviewLike.addLikes(board, member);
//        likeRepository.save(like);		//likes row추가	
//        boardRepository.plusLikeNum(boardId);		//board 의 LikeNum +1
//        return 1;	//저장 후 1반환
//    }else {
//    	
//    	//저장된 row가 있다면 board와 member를 받아와 삭제할것
//        likeRepository.deleteBoard_IdAndMember_Id(boardId, memberId);
//        boardRepository.minusLikeNum(boardId);		//board의 likeNum -1
//        return 0;	//저장 후 0반환
//    }
//}
	
	//좋아요
    @PostMapping("/like")	//row를 추가하고 1반환, row를 삭제하고 0반환
    public @ResponseBody int like(Long boardId, Long memberId) {
        int result = likeService.saveLike(boardId, memberId);
        return result;
    }

}
