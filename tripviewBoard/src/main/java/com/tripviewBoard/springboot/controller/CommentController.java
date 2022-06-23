package com.tripviewBoard.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripviewBoard.springboot.model.request.CommentRequestDto;
import com.tripviewBoard.springboot.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class CommentController {
	
	private final CommentService commentService;
	
	//create
//	 @PostMapping("/board/view/{id}/comments")   
//	 public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, 
//			 @LoginUser UserSessionDto userSessionDto) {        
//		 return ResponseEntity.ok(commentService.commentSave(userSessionDto.getNickname(), id, commentRequestDto));    
//		 }
	 
	 
    //update    
//	@PutMapping({"/view/{id}/comments/{id}"})    
//	public ResponseEntity update(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {        
//		commentService.update(id, commentRequestDto);       
//		return ResponseEntity.ok(id);    
//		}        
	
	
	//delete
//	@DeleteMapping("/view/{id}/comments/{id}")    
//	public ResponseEntity delete(@PathVariable Long id) {
//		commentService.delete(id);        
//		return ResponseEntity.ok(id);    
//		}
		
		
}
