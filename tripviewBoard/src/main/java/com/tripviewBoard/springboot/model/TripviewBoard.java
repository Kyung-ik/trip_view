package com.tripviewBoard.springboot.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "boards")
public class TripviewBoard extends BaseTimeEntity {
//테이블의 모든 필드와 builder 생성자를 구현한다.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;						//게시글 코드
	
	
	private String title;					//post 제목
	
	@Column(length = 1000)
	private String content;					//post 내용
	
	
	private double grade;					//별점(0.0~5.0)
	
	
	private int likeNum;					//좋아요 수
	
	
	private int reviewNum;					//댓글수
	
	
	private int readCnt;					//조회수
	
	private String memberNickname;

	//user
	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonManagedReference
	private Member member;
	
	//지역
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	@JsonManagedReference
	private TripviewArea area;
	
	
	public void connectArea(TripviewArea area) {
		this.area = area;
		area.connectBoard(this);
	}
	
	
//	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<TripviewComment> comments;
	
	
	//좋아요
	@JsonBackReference
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TripviewLike> likes;
	
	
	
//	//첨부파일
//	@JsonBackReference
//	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<ImgFile> imgfiles;
//	
//	
//	
//	   public void addImg(ImgFile imgFile) {
//	        this.imgfiles.add(imgFile);
//
//		// 게시글에 파일이 저장되어있지 않은 경우
//	        if(imgFile.getBoard() != this)
//	            // 파일 저장
//	            imgFile.setBoard(this);
//	    }
	
	
	   @Builder
	   public TripviewBoard(Member member, String title, String content, double grade, TripviewArea area, String memberNickname){
	      this.member = member;
	      this.title = title;
	      this.content = content;
	      this.grade = grade;
	      this.area = area;
	      this.memberNickname = memberNickname;
	   }

	   
	   public void update(String title, String content, double grade, TripviewArea area) {
	      this.title = title;
	      this.content = content;
	      this.grade = grade;
	      this.area = area;
	   }
	   
	   
	   

	
}
