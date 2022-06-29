package com.tripviewBoard.springboot.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tripviewBoard.springboot.model.AreaCode;
import com.tripviewBoard.springboot.model.BoardFileVo;
import com.tripviewBoard.springboot.model.GradeCode;
import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.MemberDto;
import com.tripviewBoard.springboot.model.TripviewArea;
import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.request.CommentRequestDto;
import com.tripviewBoard.springboot.model.request.TripviewBoardRequestDto;
import com.tripviewBoard.springboot.model.response.CommentResponseDto;
import com.tripviewBoard.springboot.model.response.TripviewBoardResponseDto;
import com.tripviewBoard.springboot.service.CommentService;
import com.tripviewBoard.springboot.service.LikeService;
import com.tripviewBoard.springboot.service.MemberService;
import com.tripviewBoard.springboot.service.TripviewBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class TripviewBoardController {
	
	@Autowired
	private final TripviewBoardService boardService;
	private final LikeService likeService;
	private final MemberService memberService;



    
	@GetMapping("list")	//게시글 list페이지로 이동
	public String getBoardListPage(Model model
			, @RequestParam(required = false, defaultValue = "0") Integer page
			, @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {
		
		try {
			model.addAttribute("resultMap", boardService.findAll(page, size));
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "board/list";
	}
	
	
	@GetMapping("list/area/{areaid}")
	public String sortArea(@PathVariable("areaid") Long id, Model model) {
		
		List<TripviewBoard> listByArea = boardService.sortArea(id);
		
		model.addAttribute("areaList", listByArea);
		
		return "board/list/area";
	}

	
	
	
	
	@GetMapping("write")	//게시글 작성페이지로 이동
	public String getBoardWritePage(Model model, TripviewBoardRequestDto boardRequest) {
		return "board/write";
	}
	
	
	
	@GetMapping("view")   //게시글 상세보기 페이지 받아오기
	   public String getBoardViewPage(Model model, TripviewBoardRequestDto boardRequest, HttpServletRequest request) throws Exception {
	      HttpSession session = request.getSession();
	      model.addAttribute("session", session.getAttribute("acount"));
	      
	      //게시글
	      try {
	         if(boardRequest.getId() != null) {
	        TripviewBoardResponseDto boardResponse = boardService.findById(boardRequest.getId());
	      model.addAttribute("info", boardResponse);
	         }
	      }catch (Exception e) {
	         throw new Exception(e.getMessage());
	      }

	      
	        //좋아요 수
//	      Long memberId = (Long)session.getAttribute("id");
//	        likeService.findLike(boardRequest.getId(), memberId);
//	        //저장된 row가 없으면 0 있으면 1
//	      
//	      Long boardId = boardRequest.getId();  
//	      int like = likeService.findLike(boardId, memberId);
//	      model.addAttribute("like", like);
	      //{like.} = ♥와 ♡를 판단할 수 있다.
	      
	      
	      return "board/view";
	   }

	
	

	
	@PostMapping("write/action")      //게시글 작성 등록하기   -> view페이지로 이동하기
	   public String boardWriteAction(Model model, TripviewBoardRequestDto boardRequest, HttpServletRequest request) throws Exception {
	      
		// 1. request에서 스트림(파일) 찾기
	      // 2. 찾은 스트림을 temp변수에 담기
	      // 3. temp에 담긴 스트림을 서버 내 폴더에 옮기고 파일명 filename에 담기
	      // 4. 데이터베이스에 저장  

	      //      try {
	      //         String origFilename = files.getOriginalFilename();
	      //         String filename = new MD5Generator(origFilename).toString();
	      //         //실행 되는 위치의 files폴더에 파일저장
	      //         String savePath = System.getProperty("user.dir") + "\\files";
	      //         //파일이 저장되는 폴더가 없으면 폴더생성
	      //         if(!new File(savePath).exists()) {
	      //            try {
	      //               new File(savePath).mkdir();
	      //            }
	      //            catch(Exception e) {
	      //               e.printStackTrace();
	      //            }
	      //         }
	      //         String filePath = savePath + "\\" + filename;
	      //         files.transferTo(new File(filePath));
	      //         
	      //         FileDto fileDto = new FileDto();
	      //         fileDto.setOrigFilename(origFilename);
	      //         fileDto.setFilename(filename);
	      //         fileDto.setFilePath(filePath);
	      //         
	      //         Long fileId = fileService.saveFile(fileDto);
	      //         fileDto.setFileId(fileId);
	      //         fileService.saveFile(fileDto);
	      //      }catch(Exception e) {
	      //         e.printStackTrace();
	      //      }
		
	      HttpSession session = request.getSession();
	      
	      MemberDto memberDto = (MemberDto)session.getAttribute("login");
	      
	      Member member = memberService.findId(memberDto);
	      
	      
	      boardRequest.setMember(member);
	      
	      try {
	         // entity를 저장하고 id값을 Long에 저장
	         Long result = boardService.save(boardRequest);
	         
	         if(result < 0) {
	            throw new Exception("#Exception boardWriteAction!");
	         }
	      }catch (Exception e) {
	         throw new Exception(e.getMessage());
	      }   
	      return "redirect:/board/list";
	   }

	
	
	
	
	 //게시글-grade
	   @ModelAttribute("gradeCode")
	   public List<GradeCode> gradeCode(){
	      List<GradeCode> gradeCode = new ArrayList<>();
	      for (int i = 50; i > 0; i--) {
	         double exNum = i*0.1;
	         double num = Math.round(exNum * 10) / 10.0;
	         String strNum = ""+num;
	         gradeCode.add(new GradeCode(num, strNum));
	      }
	      return gradeCode;
	   }

	
	
	 //게시글-area
	   @ModelAttribute("areaCode")
	   public List<AreaCode> areaCode(){
	      List<AreaCode> areaCode = new ArrayList<>();
	      areaCode.add(new AreaCode(1, "서울"));
	      areaCode.add(new AreaCode(2, "강원"));
	      areaCode.add(new AreaCode(3, "경기"));
	      areaCode.add(new AreaCode(4, "전라북도"));
	      areaCode.add(new AreaCode(5, "전라남도"));
	      areaCode.add(new AreaCode(6, "충청북도"));
	      areaCode.add(new AreaCode(7, "충청남도"));
	      areaCode.add(new AreaCode(8, "경상북도"));
	      areaCode.add(new AreaCode(9, "경상남도"));
	      areaCode.add(new AreaCode(10, "제주"));
	      areaCode.add(new AreaCode(11, "부산"));
	      areaCode.add(new AreaCode(12, "대구"));
	      areaCode.add(new AreaCode(13, "대전"));
	      areaCode.add(new AreaCode(14, "울산"));
	      areaCode.add(new AreaCode(15, "광주"));
	      return areaCode;
	   }

	
	

	   //수정 페이지로 이동하기
	   @RequestMapping("/update/{boardid}")
	   private String boardUpdateForm(@PathVariable("boardid") Long id, Model model) {
	      TripviewBoardResponseDto boardResponse = boardService.findById(id);
	      model.addAttribute("info", boardResponse);
	      return "board/update";
	   }
	   
	   
	   
	   
	 //update한 값 저장하기
	   @PostMapping("/update/action/{boardid}")
	   private String boardUpdateAction(@PathVariable("boardid") Long id,TripviewBoardRequestDto boardRequest,Model model) {
	      model.addAttribute("id", id);
	      boardService.updateBoard(id,boardRequest);      
	      
	      return "redirect:/board/list";
	   }

	
	   //상세페이지에서 게시글 삭제하기
	   @RequestMapping("/delete/{boardid}")
	   private String boardDelete(@PathVariable("boardid") Long id) {
	      boardService.deleteById(id);
	      return "redirect:/board/list";
	   }

	   
	   @PostMapping("/delete")      //게시글 목록에서 삭제하기
	   public String boardDeleteActrion(Model model, @RequestParam() Long[] deleteId) throws Exception{
	      
	      try {
	         boardService.deleteAll(deleteId);
	      } catch (Exception e) {
	         throw new Exception(e.getMessage());
	      }
	      return "redirect:/board/list";
	   }
	   
	   


	
	
	//게시글 이미지첨부
	@RequestMapping("/board/list")
	public String imgBoard(TripviewBoardRequestDto board,MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		boardService.imgBoard(board,multipartHttpServletRequest);
		return "redirect:/board/view";
	}
	
	

	
	

}
