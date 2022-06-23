package com.tripviewBoard.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.request.TripviewBoardRequestDto;
import com.tripviewBoard.springboot.model.response.TripviewBoardResponseDto;
import com.tripviewBoard.springboot.repository.MemberRepository;
import com.tripviewBoard.springboot.repository.TripviewBoardRepository;
import com.tripviewBoard.springboot.repository.TripviewLikeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
@Log
@RequiredArgsConstructor	//초기화 되지 않은 모든 final필드, @NonNull로 마크돼 있는 모든 필드들에 대한 생성자를 자동으로 생성시켜줌
@Service
public class TripviewBoardService {
	//controller에서 사용되는 메소드를 만듦
	
	@Autowired		//interface의 객체이지만 자동으로 생성할 수 있도록 도와줌
	private final TripviewBoardRepository boardRepository;
	
	@Autowired
	private final TripviewLikeRepository likeRepository;
	   
	@Autowired
	private final MemberRepository memberRepository;


	
	@Transactional
	public Long save(TripviewBoardRequestDto boardRequest) {
		return boardRepository.save(boardRequest.toEntity()).getId();			
	}
	
	
	@Transactional(readOnly = true)
	public HashMap<String, Object> findAll(Integer page, Integer size) {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		Page<TripviewBoard> list = boardRepository.findAll(PageRequest.of(page, size));
		
		resultMap.put("list", list.stream().map(TripviewBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());
		
		return resultMap;
	}
	
	
	@Transactional(readOnly = true)
	public TripviewBoardResponseDto findById(Long id) {
		boardRepository.updateReadCnt(id);
		boardRepository.plusLikeNum(id);
		return new TripviewBoardResponseDto(boardRepository.findById(id).get());
	}
	
	
	@Transactional
	public int updateBoard(TripviewBoardRequestDto boardRequest) {
		return boardRepository.updateBoard(boardRequest);
	}
	
	@Transactional
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void deleteAll(Long[] deleteId) {
		boardRepository.deleteBoard(deleteId);
	}
	
	 public void findBoard(Long id) {
	      boardRepository.findBoard(id);
	   }

	
	//페이징 처리
	private static final int BLOCK_PAGE_NUM_COUNT = 5; //블럭에 존재하는 페이지 번호 수
	private static final int PAGE_POST_COUNT = 10; //한 페이지에 존재하는 게시글 수
	
	@Transactional
	public List<TripviewBoardResponseDto> getBoardlist(Integer pageNum){
		Page<TripviewBoard> page = boardRepository.findAll(PageRequest.of(pageNum -1, PAGE_POST_COUNT,Sort.by(Sort.Direction.ASC, "createdDate")));

		List<TripviewBoard> boardEntities = page.getContent();
		List<TripviewBoardResponseDto> boardDtoList = new ArrayList<>();

		for(TripviewBoard boardEntity : boardEntities) {
			boardDtoList.add(this.convertEntityToDto(boardEntity));
		}
		return boardDtoList;
	}

	@Transactional
	public Long getBoardCount() {
		return boardRepository.count();
	}

	public Integer[] getPageList(Integer curPageNum) {
		Integer[] pageList =new Integer[BLOCK_PAGE_NUM_COUNT];

		//총 게시글 수
		Double postsTotalCount = Double.valueOf(this.getBoardCount());

		// 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
		Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

		// 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
		Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
				? curPageNum + BLOCK_PAGE_NUM_COUNT
						: totalLastPageNum;

		// 페이지 시작 번호 조정
		curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

		// 페이지 번호 할당
		for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
			pageList[idx] = val;
		}

		return pageList;

	}
	
	@Transactional
	public TripviewBoardResponseDto getPost(Long id) {
		Optional<TripviewBoard> boardEntityWrapper = boardRepository.findById(id);
		TripviewBoard boardEntity = boardEntityWrapper.get();

		TripviewBoardResponseDto boardDTO = TripviewBoardResponseDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.build();

		return boardDTO;
	}



	//검색 기능
	@Transactional
	public List<TripviewBoardResponseDto> searchPosts(String keyword) {
		List<TripviewBoard> boardEntities = boardRepository.findByTitleContaining(keyword);
		List<TripviewBoardResponseDto> boardDtoList = new ArrayList<>();

		if (boardEntities.isEmpty()) return boardDtoList;

		for (TripviewBoard boardEntity : boardEntities) {
			boardDtoList.add(this.convertEntityToDto(boardEntity));
		}

		return boardDtoList;
	}

	private TripviewBoardResponseDto convertEntityToDto(TripviewBoard boardEntity) {
		return TripviewBoardResponseDto.builder()
				.id(boardEntity.getId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.build();
	}
	
	//이미지 기능
	public void imgBoard(TripviewBoardRequestDto board,MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
			Iterator<String> filenameIterator = multipartHttpServletRequest.getFileNames();
			String name;
			while (filenameIterator.hasNext()) {
				name = filenameIterator.next();
				System.out.println("File name tag: "+name);
				List<MultipartFile> fileList = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile : fileList) {
					System.out.println("--- start file ---");
					System.out.println("File name : "+ multipartFile.getOriginalFilename());
					System.out.println("File size : "+ multipartFile.getSize());
					System.out.println("file content-type : "+ multipartFile.getContentType());
					System.out.println("--- end file ---");

				}

			}
		}
	}
	

	
}





