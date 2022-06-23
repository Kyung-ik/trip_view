package com.tripviewBoard.springboot.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "likes")
public class TripviewLike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

	
	@ManyToOne
	@JoinColumn(name = "board_id")
	@JsonManagedReference
	private TripviewBoard board;
	
	
	@ManyToOne
	@JoinColumn(name = "comment_id")
	@JsonManagedReference
	private TripviewComment comment;
	
	
	private char likeYn;
	
	
    public static TripviewLike toLikeEntity(Member member, TripviewBoard board){
    	TripviewLike likeEntity = new TripviewLike();
        likeEntity.setMember(member);;
        likeEntity.setBoard(board);

        return likeEntity;
    }
	
}
