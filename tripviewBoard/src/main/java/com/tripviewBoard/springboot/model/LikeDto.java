package com.tripviewBoard.springboot.model;

import lombok.Data;

@Data
public class LikeDto {
	
	private Long id;
	
	private Long board;
	
	private Long member;
	
	private int heart;
	
	
}
