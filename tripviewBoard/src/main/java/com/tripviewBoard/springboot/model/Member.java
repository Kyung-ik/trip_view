package com.tripviewBoard.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tripviewBoard.springboot.model.BaseTimeEntity;
import com.tripviewBoard.springboot.model.TripviewBoard;
import com.tripviewBoard.springboot.model.TripviewComment;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="tirpview_userInfo")
public class Member extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "acount")
	private String acount;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "password2")
	private String password2;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "phonenumber")
	private String phonenumber;
	
	@Column(name = "rememberAcount")
	private Boolean rememberAcount;
	
	@Builder
	public Member(String acount, String password, String name, String gender,String nickname,String phonenumber) {
		this.acount = acount;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.nickname = nickname;
		this.phonenumber = phonenumber;
	}
	
	public void update(String password, String nickname, String phonenumber) {
		this.password = password;
		this.nickname = nickname;
		this.phonenumber = phonenumber;
	}
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TripviewBoard> boards;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TripviewComment> comments;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TripviewLike> likes;
	
	
	

}

