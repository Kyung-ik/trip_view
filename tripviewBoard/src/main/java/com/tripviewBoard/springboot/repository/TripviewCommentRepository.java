package com.tripviewBoard.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripviewBoard.springboot.model.TripviewComment;

public interface TripviewCommentRepository extends JpaRepository<TripviewComment, Long> {

}
