package com.tripviewBoard.springboot.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	
	
	//1
    public static TripviewLike addLikes(TripviewBoard board, Member member){
        TripviewLike likes = new TripviewLike();
        likes.setBoard(board);
        likes.setMember(member);

        return likes;
    }
	
}
