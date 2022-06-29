package com.tripviewBoard.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripviewBoard.springboot.model.ImgFile;

public interface ImgFileRepository extends JpaRepository<ImgFile, Long> {
	
	
}
