package com.tripviewBoard.springboot.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tripviewBoard.springboot.model.BoardFileVo;
import com.tripviewBoard.springboot.model.Member;
import com.tripviewBoard.springboot.model.MemberDto;
import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.request.TripviewBoardRequestDto;
import com.tripviewBoard.springboot.model.response.TripviewBoardResponseDto;
import com.tripviewBoard.springboot.repository.MemberRepository;
import com.tripviewBoard.springboot.repository.TripviewBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final TripviewBoardRepository boardRepository;
	
	
	public MemberDto findById(Long id) {
		return new MemberDto(memberRepository.findById(id).get());
	}
	
	
	//중복 회원 체크
	public boolean joinCheck(MemberDto dto) {
		Member findMember = memberRepository.findByAcount(dto.getAcount());
		
		if (findMember == null) {
			return true;
		} else {
			return false;
		}
	}
	
	//ID찾기
	public Member findId(MemberDto dto) {
		Member findMember = memberRepository.findByNameAndPhonenumber(dto.getName(),dto.getPhonenumber());
		
		if (findMember != null) {
			return findMember;
		} else {
			return null;
		}
	}
	
	//PW찾기
	public String findPw(MemberDto dto) throws NoSuchAlgorithmException {
		Member findMember = memberRepository.findByAcountAndNameAndPhonenumber(dto.getAcount(),dto.getName(),dto.getPhonenumber());
		
		if (findMember != null) {
			Random random = new Random();
			int bound = 10000;
			String temPassword = String.valueOf(random.nextInt(bound));
			findMember.setPassword(encrypt(temPassword));
			return temPassword;
		} else {
			return null;
		}
	}
	
	
	//회원가입
	@Transactional
	public Long join(MemberDto memberDto) throws NoSuchAlgorithmException{
		memberDto.passwordEncoding(encrypt(memberDto.getPassword()));
		Member member = memberDto.toEntity();
		Member findMember = memberRepository.findByAcount(member.getAcount());
		if (findMember == null) {
			memberRepository.save(member);
			return member.getId();
		}else {
			return null;
		}
	}
	
	//아이디 중복체크
	public boolean checkAcountDuplicate(String acount) {
		return memberRepository.existsByAcount(acount);
	}
	
	//닉네임 중복체크
	public boolean checkNicknameDuplicate(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}
	
	
	//패스워드 암호화
	public static String encrypt(String password) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] passByte = password.getBytes(StandardCharsets.UTF_8);
		md.reset();
		byte[] digested = md.digest(passByte);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digested.length; i++) {
			sb.append(Integer.toHexString(0xff & digested[i]));
		}
		return sb.toString();
	}
	
	//로그인
	public MemberDto login(MemberDto memberDto) throws Exception{
		
		Member findMember = memberRepository.findByAcount(memberDto.getAcount());
		
		memberDto.passwordEncoding(encrypt(memberDto.getPassword()));
		if(findMember != null) {
			System.out.println("TEST2");
			MemberDto findMemberDto = new MemberDto(findMember);
			if (memberDto.getPassword().equals(findMemberDto.getPassword())) {
				return findMemberDto;
			}
		}
		return null;
		
		//로그인 시 입력된 MemberDto를 받아서 회원이 존재하면 입력받은 비밀번호를 암호화 하여 DB의 해당
		//비밀번호를 비교하고 로그인한다.
	}
	
	//개인정보 수정
	@Transactional
	public void update(MemberDto memberDto) {
		Member member = memberRepository.findByAcount(memberDto.getAcount());
		member.update(
				memberDto.getPassword(), 
				memberDto.getNickname(), 
				memberDto.getPhonenumber());
	}

}
