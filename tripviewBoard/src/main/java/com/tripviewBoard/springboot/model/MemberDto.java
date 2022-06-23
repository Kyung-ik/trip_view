package com.tripviewBoard.springboot.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
public class MemberDto {
	
	private Long id;
	
	@NotBlank(message = "아이디를 입력하세요.")
	@Size(min = 4, message="4자 이상입니다.")
	private String acount;
	
	@NotBlank(message = "비밀번호를 입력하세요.")
	@Size(min = 2, message="2자 이상입니다.")
	private String password;
	
	@NotBlank(message = "비밀번호를 입력하세요")
	@Size(min = 2, message="2자 이상입니다.")
	private String password2;
	
	@NotBlank(message = "이름을 입력하세요.")
	private String name;
	
	@NotBlank(message = "성별을 체크하세요.")
	private String gender;
	
	@NotBlank(message = "닉네임을 입력하세요.")
	private String nickname;
	
	@NotBlank(message = "전화번호를 입력하세요.")
	@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message ="올바른 휴대폰 번호를 입력하세요.")
	private String phonenumber;
	
	public MemberDto(Member member) {
		id = member.getId();
		acount = member.getAcount();
		password = member.getPassword();
		password2 = member.getPassword2();
		name = member.getName();
		gender = member.getGender();
		nickname = member.getNickname();
		phonenumber = member.getPhonenumber();
	}
	
	@Builder
	public MemberDto(String acount, String password,String name, String gender, String nickname, String phonenumber) {
		this.acount = acount;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.nickname = nickname;
		this.phonenumber = phonenumber;
	}
	
	public Member toEntity() {
		 return Member.builder()
				 .acount(acount)
				 .password(password)
				 .name(name)
				 .gender(gender)
				 .nickname(nickname)
				 .phonenumber(phonenumber)
				 .build();
	}
	//비밀번호는 Service에서 암호화 할 것이다.
	public void passwordEncoding(String encodingPassword) {
		this.password = encodingPassword;
	}

}
