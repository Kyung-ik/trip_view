package com.tripview.dto;

import java.time.LocalDateTime;

import com.tripview.domain.entity.BoardEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
	private Long id;
	private String writer;
	private String title;
	private String content;
	private int readCnt;
	private int likeNum;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public BoardEntity toEntity() {
		BoardEntity boardEntity = BoardEntity.builder()
				.id(id)
				.writer(writer)
				.title(title)
				.content(content)
				.likeNum(likeNum)
				.readCnt(readCnt)
				.build();
		return boardEntity;
	}
	
	@Builder
	public BoardDto(Long id, String title, String content, String writer,int likeNum,int readCnt, LocalDateTime createdDate, LocalDateTime modifiedDate) {
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.likeNum = likeNum;
		this.readCnt = readCnt;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}
