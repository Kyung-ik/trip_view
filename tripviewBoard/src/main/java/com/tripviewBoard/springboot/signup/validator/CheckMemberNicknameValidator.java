package com.tripviewBoard.springboot.signup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.tripviewBoard.springboot.model.MemberDto;
import com.tripviewBoard.springboot.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckMemberNicknameValidator extends AbstractValidator<MemberDto>{
	
	private final MemberRepository memeberRepository;
	
	@Override
	protected void doValidate(MemberDto memberdto,Errors errors) {
		if (memeberRepository.existsByNickname(memberdto.toEntity().getNickname())) {
			errors.rejectValue("nickname", "닉네임 중복오류", "이미 사용중인 닉네임입니다.");
		}
	}

}
