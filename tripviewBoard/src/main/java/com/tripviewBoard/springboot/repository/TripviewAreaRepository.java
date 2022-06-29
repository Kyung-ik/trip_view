package com.tripviewBoard.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripviewBoard.springboot.model.TripviewArea;

public interface TripviewAreaRepository extends JpaRepository<TripviewArea, Long> {
	 TripviewArea findByName(String name);
}
