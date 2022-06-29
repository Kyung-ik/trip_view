package com.tripviewBoard.springboot.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tripviewBoard.springboot.model.TripviewLike;

public interface TripviewLikeRepository extends JpaRepository<TripviewLike, Long> {
	
	static final String FIND_BOARDID_AND_MEMBERID = "SELECT COUNT(*) FROM likes "
			+ "WHERE board_id = :boardId AND member_id = :memberId";
	
	
	static final String DELETE_BOARDID_AND_MEMBERID = "DELETE FROM likes "
		    + "WHERE board_id = :boardId AND member_id = :memberId";
	
	
	
	//boardid 와 memberid를 인자로 받아 해당 게시물에 회원이 좋아요를 누른적 있는지 확인하기 2
	@Transactional
	@Modifying
	@Query(value = FIND_BOARDID_AND_MEMBERID, nativeQuery = true)
	int findBoard_IdAndMember_Id(Long boardId, Long memberId);

	
	@Transactional
	@Modifying
	@Query(value = DELETE_BOARDID_AND_MEMBERID, nativeQuery = true)
	void  deleteBoard_IdAndMember_Id(Long boardId, Long memberId);
	
	

}
