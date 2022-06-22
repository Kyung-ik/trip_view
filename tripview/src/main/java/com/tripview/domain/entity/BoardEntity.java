package com.tripview.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="board2")
public class BoardEntity extends TimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10, nullable = false)
	private String writer;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	@Column(length = 11, nullable = false)
	private int readCnt;
	@Column(length = 11, nullable = false)
	private int likeNum;
	
	@Builder
	public BoardEntity(Long id, String title, String content, String writer,int readCnt,int likeNum) {
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.likeNum = likeNum;
	}
	
}
