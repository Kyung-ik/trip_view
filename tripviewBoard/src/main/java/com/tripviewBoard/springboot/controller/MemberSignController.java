package com.tripviewBoard.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.MemberDto;
import com.tripviewBoard.springboot.service.MemberService;
import com.tripviewBoard.springboot.signup.validator.CheckMemberAcountValidator;
import com.tripviewBoard.springboot.signup.validator.CheckMemberNicknameValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class MemberSignController {
	
	private final MemberService memberService;
	private final CheckMemberAcountValidator checkMemberAcountValidator;
	private final CheckMemberNicknameValidator checkMemberNicknameValidator;
	
	//커스텀 유효 검증을 위해추가
	@InitBinder
	public void validatorBinder(WebDataBinder binder) {
		binder.addValidators(checkMemberAcountValidator);
		binder.addValidators(checkMemberNicknameValidator);
	}
	
	//회원가입 겟, 포스트
	@GetMapping("/signup")
	public String signUp(MemberDto dto) {
		return "/signup/signup";
	}

	@PostMapping("/signup")
	public String signUpPost(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult, Model model) throws Exception{
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
			model.addAttribute("memberDto", memberDto);
			Map<String, String>	errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(error.getDefaultMessage());
			}
			return "/signup/signup";
			
		}else {
//			if (memberService.joinCheck(memberDto)) {
				memberService.join(memberDto);
//			}
		}
		return "redirect:/board/index";
	}
	
	
	//회원가입시 아이디랑 닉네임 중복확인
	@GetMapping("/member-acount/{acount}/exists")
	public ResponseEntity<Boolean> checkAcountDuplicate(@PathVariable String acount){
		return ResponseEntity.ok(memberService.checkAcountDuplicate(acount));
	}
	
	@GetMapping("/member-nickname/{nickname}/exists")
	public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname){
		return ResponseEntity.ok(memberService.checkNicknameDuplicate(nickname));
	}
	
	
//	@PostMapping("/idCheck")
//	@ResponseBody
//	public boolean idCheck(@ModelAttribute MemberDto memberDto) {
//		boolean count = memberService.joinCheck(memberDto);
//		return count;
//	}z
	
	
	//로그인 겟이랑 포스트 
	@GetMapping("/login")
	public String login(Model model) {
		MemberDto memberDto = new MemberDto();
		model.addAttribute("memberDto", memberDto);
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDto memberDto, HttpServletRequest request,BindingResult bindingResult, Model model) throws Exception{
		HttpSession session = request.getSession();
		MemberDto findMemberDto = memberService.login(memberDto);
		if(findMemberDto != null) {
			session.setAttribute("login", findMemberDto);
			session.setAttribute("ACOUNT", findMemberDto.getAcount());
			session.setAttribute("NAME", findMemberDto.getName());
			session.setAttribute("NICKNAME", findMemberDto.getNickname());
			session.setAttribute("ID", findMemberDto.getId());
		}else {
			//JOptionPane.showMessageDialog(null, "ID나 비밀번호정보가 없거나 다릅니다.");
			model.addAttribute("message","로그인 정보가 틀렸습니다.");
			return "login/loginFormN";
		}
		return "redirect:/board/index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		request.getSession(true);
		return "redirect:/board/index";
	}
	
	//회원 수정 페이지
	@GetMapping("/update")
	public String userUpdate(MemberDto dto) {
		return "member/userUpdate";
	}
	
	
	
	//아이디 찾기/
	@GetMapping("/findid")
	public String findId(MemberDto dto){ 
		return "member/find_id";
	}
	@PostMapping("/findid")
	public String findId(@ModelAttribute MemberDto memberDto, Model model) throws Exception{
		Member findIdcheckValue = memberService.findId(memberDto);
		if(findIdcheckValue != null) {
			//System.out.println(findIdcheckValue.getAcount());
			
			//찾아낸 아이디 맨 뒷자리는 **로 표시하기위함.
			int length =findIdcheckValue.getAcount().length()-2;
			String findAcount = findIdcheckValue.getAcount().substring(0, length);
			findAcount += "**";
			model.addAttribute("id", findAcount);
			return "member/findidcheckY";
		}else {
			model.addAttribute("message","입력한 정보가 없거나 틀립니다.");
			return "member/findidcheckN";
		}
	}
	
	//비밀번호 찾기/
	@GetMapping("/findpw")
	public String findPw(MemberDto dto){ 
		return "member/find_pw";
	}
	
	@PostMapping("/findpw")
	public String findPw(@ModelAttribute MemberDto memberDto, Model model) throws Exception{
		String password = memberService.findPw(memberDto);
		System.out.println("1");
		if(password != null) {
			System.out.println(password);
			model.addAttribute("pw", password);
			return "member/findpwcheckY";
		}else {
			model.addAttribute("message","입력한 정보가 없거나 틀립니다.");
			return "member/findpwcheckN";
		}
	}
	

}