package com.tripviewBoard.springboot.model.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tripviewBoard.springboot.model.TripviewComment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;    
    private String content;    
    private LocalDateTime createdDate = LocalDateTime.now();   
    private LocalDateTime modifiedDate = LocalDateTime.now();    
    private String nickname;    
    private Long boardId;     
    
    
    /* Entity -> Dto*/    
    public CommentResponseDto(TripviewComment comment) {
    	this.id = comment.getId();        
    	this.content = comment.getContent();
    	this.createdDate = comment.getCreatedDate();
    	this.nickname = comment.getMember().getNickname();       
    	this.boardId = comment.getBoard().getId();    
    	}
}
