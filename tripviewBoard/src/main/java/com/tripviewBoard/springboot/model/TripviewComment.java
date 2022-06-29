package com.tripviewBoard.springboot.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class TripviewComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;			//댓글 코드
	private LocalDateTime createdDate = LocalDateTime.now(); 	//댓글 생성 날짜
	private String content;		//댓글 내용
	private int likeNum;		//댓글 좋아요 수
	
	@Builder
	public TripviewComment(String content) {
		this.content = content;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonManagedReference
	private Member member;
	
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	@JsonManagedReference
	private TripviewBoard board;
	
	
	public void update(String content) {
		this.content = content;
	}
	
}
