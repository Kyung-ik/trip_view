package com.tripviewBoard.springboot;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripviewBoard.springboot.model.request.TripviewBoardRequestDto;
import com.tripviewBoard.springboot.model.response.TripviewBoardResponseDto;
import com.tripviewBoard.springboot.service.TripviewBoardService;

@SpringBootTest
class TripviewBoardApplicationTests {
	
	@Autowired
	private TripviewBoardService tripviewBoardService;
	
	
}







