package com.kor.java.proj.dto;

public class Member {
	public int id;
	public String loginId;
	public String loginPw;
	public String name;
	public String time;

	public Member(int id, String loginId, String loginPw, String name, String time) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.time = time;
	}

}