package com.tripviewBoard.springboot.model.request;

import javax.validation.constraints.NotBlank;

import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.TripviewArea;
import com.tripviewBoard.springboot.model.TripviewBoard;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class TripviewBoardRequestDto {
//게시글에 입력받을 것
	private Long id;
	
	@NotBlank(message = "제목을 입력해주세요")
	private String title;
	
	@NotBlank(message = "내용을 입력해주세요")
	private String content;
	
	private double grade;
	
	private Member member;	
	
	@NotBlank(message = "지역을 선택해주세요")
	private TripviewArea area;
	
	
	

	public TripviewBoard toEntity() {
		return TripviewBoard.builder()
				.member(member)
				.title(title)
				.content(content)
				.grade(grade)
				.area(area)
				.memberNickname(member.getNickname())
				.build();
	}
	 public void setMember(Member member) {
	      this.member = member;
	   }

	
}
