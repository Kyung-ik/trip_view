package com.tripviewBoard.springboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.request.TripviewBoardRequestDto;
import com.tripviewBoard.springboot.model.response.TripviewBoardResponseDto;

public interface TripviewBoardRepository extends JpaRepository<TripviewBoard, Long> {
//CRUD의 기능을 담당하는 인터페이스 생성
	
	List<TripviewBoard> findByTitleContaining(String keyword);
	
	String UPDATE_BOARD = "UPDATE boards "
	         + "SET TITLE = :#{#TripviewBoardRequestDto.title}, "
	         + "CONTENT = :#{#TripviewBoardRequestDto.content}, "
	         + "GRADE = :#{#TripviewBoardRequestDto.grade}, "
	         + "AREAID = :#{#TripviewBoardRequestDto.areaId}, "
	         + "MODIFIEDTIME = Now()"
	         + "WHERE ID = {#TripviewBoardRequestDto.id}";
	   
	   
	   static final String UPDATE_READ_CNT = "UPDATE boards "
	         + "SET READ_CNT = READ_CNT + 1 "
	         + "WHERE ID = :id";
	   
	   
	   static final String PLUS_LIKE_NUM = "UPDATE boards "
	         + "SET LIKE_NUM = LIKE_NUM + 1 "
	         + "WHERE ID = :id";
	   
	   static final String MINUS_LIKE_NUM = "UPDATE boards "
	         + "SET LIKE_NUM = LIKE_NUM - 1 "
	         + "WHERE ID = :id";
	   
	   
	   static final String DELETE_BOARD = "DELETE FROM boards "
	         + "WHERE ID IN (:deleteList)";
	         
	   
	   static final String FIND_ALL = "SELECT * FROM boards "
	         + "WHERE ID={#BOARD_ID}";
	   

	   @Transactional
	   @Modifying
	   @Query(value = UPDATE_BOARD, nativeQuery = true)
	   public int updateBoard(@Param("TripviewBoardRequestDto") TripviewBoardRequestDto tripviewBoardRequestDto);
	   
	   
	   @Transactional
	   @Modifying
	   @Query(value = UPDATE_READ_CNT, nativeQuery = true)
	   public int updateReadCnt(@Param("id") Long id);
	   
	   
	   @Transactional
	   @Modifying
	   @Query(value = PLUS_LIKE_NUM, nativeQuery = true)
	   public int plusLikeNum(@Param("id") Long id);
	   
	   
	   @Transactional
	   @Modifying
	   @Query(value = MINUS_LIKE_NUM, nativeQuery = true)
	   public int minusLikeNum(@Param("id") Long id);
	   
	   
	   @Transactional
	   @Modifying
	   @Query(value = DELETE_BOARD, nativeQuery = true)
	   public int deleteBoard(@Param("deleteList") Long[] deleteList);
	   
	   
	   @Transactional
	   @Modifying
	   @Query(value = FIND_ALL, nativeQuery = true)
	   public TripviewBoardResponseDto findBoard(@Param("boardid") Long id);
	   

}
