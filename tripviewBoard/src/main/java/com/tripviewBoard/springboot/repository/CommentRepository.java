package com.tripviewBoard.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripviewBoard.springboot.model.TripviewComment;

public interface CommentRepository extends JpaRepository<TripviewComment, Long> {

}
