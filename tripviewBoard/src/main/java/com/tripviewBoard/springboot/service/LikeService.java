package com.tripviewBoard.springboot.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.TripviewLike;
import com.tripviewBoard.springboot.repository.CommentRepository;
import com.tripviewBoard.springboot.repository.MemberRepository;
import com.tripviewBoard.springboot.repository.TripviewBoardRepository;
import com.tripviewBoard.springboot.repository.TripviewLikeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeService {
		
		
		private final TripviewLikeRepository likeRepository;
		private final TripviewBoardRepository boardRepository;
		private final MemberRepository memberRepository;
	
	    public int findLike(Long boardId, Long memberId) {
	        // 저장된 DTO 가 없다면 0, 있다면 1

	        Optional<TripviewLike> findLike = likeRepository.findByBoard_IdAndMember_Id(boardId, memberId);


	        if (findLike.isEmpty()){
	            return 0;
	        }else {

	            return 1;
	        }
	    }

	    @Transactional
	    public int saveLike(Long boardId, Long memberId) {
	        Optional<TripviewLike> findLike = likeRepository.findByBoard_IdAndMember_Id(boardId, memberId);

	        System.out.println(findLike.isEmpty());

	        if (findLike.isEmpty()){
	            Member member = memberRepository.findById(memberId).get();
	            TripviewBoard board = boardRepository.findById(boardId).get();

	            TripviewLike like = TripviewLike.toLikeEntity(member, board);
	            likeRepository.save(like);
	            boardRepository.plusLikeNum(boardId);
	            return 1;
	        }else {
	            likeRepository.deleteByBoard_IdAndMember_Id(boardId, memberId);
	            boardRepository.minusLikeNum(boardId);
	            return 0;

	        }
}
	    
}
