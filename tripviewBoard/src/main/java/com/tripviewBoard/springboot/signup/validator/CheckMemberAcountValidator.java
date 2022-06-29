package com.tripviewBoard.springboot.signup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.tripviewBoard.springboot.model.MemberDto;
import com.tripviewBoard.springboot.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckMemberAcountValidator extends AbstractValidator<MemberDto>{
	
	private final MemberRepository memeberRepository;
	
	@Override
	protected void doValidate(MemberDto memberdto,Errors errors) {
		if (memeberRepository.existsByAcount(memberdto.toEntity().getAcount())) {
			errors.rejectValue("acount", "아이디 중복오류", "이미 사용중인 아이디입니다.");
		}
	}

}
