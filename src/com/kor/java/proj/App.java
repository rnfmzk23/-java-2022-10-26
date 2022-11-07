package com.kor.java.proj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.proj.controller.ArticleController;
import com.kor.java.proj.controller.MemberController;
import com.kor.java.proj.dto.Article;
import com.kor.java.proj.dto.Member;
import com.kor.java.proj.util.Util;

public class App {
	List<Article> articles;
	List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		
		MemberController membercontroller = new MemberController(sc, members);
		ArticleController articlecontroller = new ArticleController(sc, articles);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			command = command.trim();

			if (command.length() == 0) {
				continue;
			}
			if (command.equals("system exit")) {
				break;
			} 
//			<회원>
			else if (command.equals("member join")) {
				membercontroller.doJoin();
			}
//			<게시물>
			else if (command.equals("article write")) {
				articlecontroller.dowrite();
			} else if (command.equals("article list")) {
				articlecontroller.showlist();

			} else if (command.startsWith("article detail ")) {
				articlecontroller.showdetail(command);
			} else if (command.startsWith("article delete ")) {
				articlecontroller.dodelete(command);
			} else if (command.startsWith("article modify ")) {
				articlecontroller.domodify(command);
			} 
				 else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
			}
		}

		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
	
	
	

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, "제목1", "aaaa", Util.getNowDateStr()));
		articles.add(new Article(2, "제목2", "bbbb", Util.getNowDateStr()));
		articles.add(new Article(3, "제목3", "cccc", Util.getNowDateStr()));
	}
}
