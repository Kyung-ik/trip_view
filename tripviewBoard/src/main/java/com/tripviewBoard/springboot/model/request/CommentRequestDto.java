package com.tripviewBoard.springboot.model.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.TripviewComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long id;    
    private String content;    
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));    
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));    
    private Member member;    
    private TripviewBoard board;

    
    
    
    public TripviewComment toEntity() {        
    	TripviewComment comments = TripviewComment.builder()           
    			.content(content)
    			.build();         
    	return comments;    
    	}
}
