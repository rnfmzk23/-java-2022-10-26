package com.kor.java.proj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.proj.dto.Article;
import com.kor.java.proj.dto.Member;
import com.kor.java.proj.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	String command;
	String actionName;
	private Member loginedMember;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = members;
		
		members = new ArrayList<Member>();
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		members.add(new Member(1, "admin", "admin", "관리자", Util.getNowDateStr()));
		members.add(new Member(2, "user1", "user1", "유저1", Util.getNowDateStr()));
		members.add(new Member(3, "user2", "user2", "유저2", Util.getNowDateStr()));
	}
	
	public void doAction(String command, String actionName) {
		this.command = command;
		this.actionName = actionName;

		switch(actionName) {
		
		case "join" :
			doJoin();
			break;
			
		case "login" :
			doLogin();
			break;
			
		default: 
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void doLogin() {
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		// 입력받은 아이디에 해당하는 회원이 존재하는지

		Member member = getMemberByLoginId(loginId);

		if ( member == null ) {
			System.out.println("해당회원은 존재하지 않습니다.");
			return;
		}

		if ( member.loginPw.equals(loginPw) == false ) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return;
		}

		loginedMember = member;
		System.out.printf("로그인 되었습니다, %s님 환영합니다.\n", loginedMember.name);
	}

	public void doJoin() {
		int id = members.size() + 1;

		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
				continue;
			}

			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("PW : ");
			loginPw = sc.nextLine();

			System.out.printf("PW 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm)) {
				break;
			}
			System.out.println("비밀번호를 다시 입력해주세요.");
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		String WriteTime = Util.getNowDateStr();

		Member member = new Member(id, loginId, loginPw, name, WriteTime);
		members.add(member);

		System.out.printf("%d번 회원이 생성되었습니다. 환영합니다.\n", id);
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if ( index == -1 ) {
			return null;
		}

		return members.get(index);
	}
	
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}

		return -1;
	}
}
