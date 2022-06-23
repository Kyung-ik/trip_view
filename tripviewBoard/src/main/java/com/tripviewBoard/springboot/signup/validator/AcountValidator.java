package com.tripviewBoard.springboot.signup.validator;
//package com.tripviewBoard.springboot.signup.service;
//
//import javax.validation.Validator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//
//import com.tripview.signup.member.MemberDto;
//
//@Component
//public class AcountValidator implements Validator{
//	
//	@Autowired
//	private MemberRepository memberRepository;
//	
//	@Override
//	public boolean supports(Class<?> clazz) {
//		return MemberDto.class.equals(clazz);
//	}
//	
//	@Override
//	public void validate(Object obj, Errors errors) {
//		MemberDto memberDto = (MemberDto) obj;
//		if(!((MemberDto) obj).getPassword().equals(((MemberDto) obj).getPassword2())) {
//			//비밀번호와 비밀번호 확인이 다르다면
//			errors.rejectValue("password", "비밀번호가 일치하지 않습니다.");
//		}else if(memberRepository.findByAcount(((MemberDto) obj).getAcount()) !=null){
//			//아이디 존재한다
//			errors.reject("acount","이미 사용중인 아이디입니다.");
//			
//		}
//	}
//
//}
