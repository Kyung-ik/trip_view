package com.tripviewBoard.springboot.signup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.tripviewBoard.springboot.model.MemberDto;
import com.tripviewBoard.springboot.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckMemberPasswordValidator extends AbstractValidator<MemberDto>{
	
	private final MemberRepository memeberRepository;
	
	@Override
	protected void doValidate(MemberDto memberdto,Errors errors) {
		if (memeberRepository.existsByNickname(memberdto.toEntity().getNickname())) {
			errors.rejectValue("password", "비밀번호 확인오류", "패스워드 확인이 다릅니다.");
		}
		
		//if (memeberRepository.existsByPassword((memberdto.toEntity().getPassword()).equals(memberdto.toEntity().getPassword2()))) {
		//	errors.rejectValue("password2", "비밀번호 확인오류", "패스워드 확인이 다릅니다.");
		//}
	}
}
