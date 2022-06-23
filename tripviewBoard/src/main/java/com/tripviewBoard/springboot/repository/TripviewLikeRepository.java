package com.tripviewBoard.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripviewBoard.springboot.model.TripviewLike;

public interface TripviewLikeRepository extends JpaRepository<TripviewLike, Long> {
	 Optional<TripviewLike> findByBoard_IdAndMember_Id(Long boardId, Long memberId);
	    void  deleteByBoard_IdAndMember_Id(Long boardId, Long memberId);
}
