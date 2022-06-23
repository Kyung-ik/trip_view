package com.tripviewBoard.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.TripviewComment;
import com.tripviewBoard.springboot.model.request.CommentRequestDto;
import com.tripviewBoard.springboot.repository.CommentRepository;
import com.tripviewBoard.springboot.repository.MemberRepository;
import com.tripviewBoard.springboot.repository.TripviewBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
    private final CommentRepository commentRepository;
    private final TripviewBoardRepository boardRepository;
    private final MemberRepository memberRepository;

//    //create 
//    @Transactional    
//    public Long commentSave(String nickname, Long id, CommentRequestDto commentRequestDto) {        
//    	Member member = memberRepository.findByNickname(nickname);        
//    	TripviewBoard board = boardRepository.findById(id).orElseThrow(() ->  
//    	new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id)); 
//    	
//    	commentRequestDto.setMember(member);       
//    	commentRequestDto.setBoard(board);  
//    	
//    	TripviewComment comment = commentRequestDto.toEntity();       
//    	commentRepository.save(comment); 
//    	
//    	return commentRequestDto.getId();    
//    	}
//    
//    
//    //update   
//    @Transactional    
//    public void update(Long id, CommentRequestDto commentRequestDto) {        
//    	TripviewComment comment = commentRepository.findById(id).orElseThrow(() ->
//    	new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));
//    	comment.update(commentRequestDto.getContent());    
//    	}        
//    
//    
//    //delete 
//    @Transactional    
//    public void delete(Long id) {        
//    	TripviewComment comment = commentRepository.findById(id).orElseThrow(() ->                
//    	new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
//    	commentRepository.delete(comment);    
//    	}
}
