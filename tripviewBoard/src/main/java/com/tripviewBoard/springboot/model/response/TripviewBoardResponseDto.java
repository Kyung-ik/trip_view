package com.tripviewBoard.springboot.model.response;

import java.time.LocalDateTime;

import com.tripviewBoard.springboot.model.TripviewBoard;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TripviewBoardResponseDto {
	//게시글에 출력할 것
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createTime;
	private LocalDateTime modifiedTime;
	private double grade;
	private int likeNum;
	private int reviewNum;
	private int readCnt;
	private Long member;
	private String area;
	private String memberNickname;  
	//   private List<CommentResponseDto> comments;

	public TripviewBoardResponseDto(TripviewBoard entity){
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.createTime = entity.getCreateTime();
		this.modifiedTime = entity.getModifiedTime();
		this.grade = entity.getGrade();
		this.likeNum = entity.getLikeNum();
		this.reviewNum = entity.getReviewNum();
		this.readCnt = entity.getReadCnt();
		this.member = entity.getMember().getId();
		this.area = entity.getArea().getName();		
		this.memberNickname = entity.getMember().getNickname();
		//		this.comments = entity.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());   
	}

	public TripviewBoard toEntity() {
		TripviewBoard boardEntity = TripviewBoard.builder()
				.title(title)
				.content(content)
				.build();
		return boardEntity;
	}
	@Builder
	public TripviewBoardResponseDto(Long id,String title,String content, double grade,Long member) {
		this.id =id;
		this.title =title;
		this.content = content;
		this.grade = grade;
		this.member = member;
	}
}

