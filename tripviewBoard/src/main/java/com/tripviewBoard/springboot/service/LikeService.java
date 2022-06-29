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
	
		//2	//tripviewLikeRopository에서 좋아요 한 row찾기
		//저장된 row가 있는지 확인하는 메소드
		    public int findLike(Long boardId, Long memberId) {
	        int findLike = likeRepository.findBoard_IdAndMember_Id(boardId, memberId);
	        
	        if (findLike == 0){	//없으면 0 있으면 1
	            return 0;
	        }else {
	            return 1;
	        }
	    }

	    
	    //저장하는 메소드
	    @Transactional
	    public int saveLike(Long boardId, Long memberId) {
	    	
	    	int findLike = likeRepository.findBoard_IdAndMember_Id(boardId, memberId);


	        if (findLike==0){	//4 //저장된 row가 없다면 board와 member를 받아 tripviewLike의 추가하는 메소드로 이동
	        	TripviewBoard board = boardRepository.findById(boardId).get();
	            Member member = memberRepository.findById(memberId).get();
	            
	            TripviewLike like = TripviewLike.addLikes(board, member);
	            likeRepository.save(like);		//likes row추가	
//	            boardRepository.plusLikeNum(boardId);		//board 의 LikeNum +1
	            return 1;	//저장 후 1반환
	        }else {
	        	
	        	//저장된 row가 있다면 board와 member를 받아와 삭제할것
	            likeRepository.deleteBoard_IdAndMember_Id(boardId, memberId);
//	            boardRepository.minusLikeNum(boardId);		//board의 likeNum -1
	            return 0;	//저장 후 0반환
	        }
	    }
	    
}
