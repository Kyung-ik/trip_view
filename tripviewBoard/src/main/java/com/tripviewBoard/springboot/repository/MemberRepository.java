package com.tripviewBoard.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tripviewBoard.springboot.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Member findByAcount(String acount);
	public Member findByNickname(String nickname);
	
	//ID,PW찾기
	public Member findByNameAndPhonenumber(String name,String phonenumber);
	public Member findByAcountAndNameAndPhonenumber(String acount, String name, String phonenumber);
	
	//회원가입시 아이디,닉네임 중복확인
	public boolean existsByAcount(String acount);
	public boolean existsByNickname(String nickname);
}
