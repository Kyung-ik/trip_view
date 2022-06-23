package com.tripviewBoard.springboot.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardFileVo {
	private Long memberId;
	private String title;
	private String content;
	private String availableYn;
	private double grade;
	private String area;
	private String memberNickname;
	
	private List<MultipartFile> files;
	
	
	
	

	

}
