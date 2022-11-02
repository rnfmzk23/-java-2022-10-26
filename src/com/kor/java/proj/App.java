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
		
		ArticleController articlecontroller = new ArticleController();
		MemberController membercontroller = new MemberController();

		Scanner sc = new Scanner(System.in);

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
			
//			<게시물>
			else if (command.equals("article write")) {
				int id = articles.size() + 1;

				System.out.printf("제목 : ");
				String title = sc.nextLine();

				System.out.printf("내용 : ");
				String body = sc.nextLine();

				String WriteTime = Util.getNowDateStr();

				Article article = new Article(id, title, body, WriteTime);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				System.out.println(" 번호 |  조회 | 제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);
				}

			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]); // "1" -> 1

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				foundArticle.increaseHit();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.time);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]); // "1" -> 1

				int foundIndex = getArticleIndexById(id);

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				articles.remove(foundIndex);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]); // "1" -> 1

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String title = sc.nextLine();

				System.out.printf("내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
			} 
			
//			<회원>
			else if (command.equals("member join")) {
				int id = members.size() + 1;
				
				String loginId = null;
				
				while ( true ) {
					System.out.printf("로그인 아이디 : ");
					loginId = sc.nextLine();

					if ( isJoinableLoginId(loginId) == false ) {
						System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
						continue;
					}

					break;
				}
				
				String loginPw = null;
				String loginPwConfirm = null;
				
				while(true) {	
					System.out.printf("PW : ");
					loginPw = sc.nextLine();
					
					System.out.printf("PW 확인 : ");
					loginPwConfirm = sc.nextLine();
					
					if(loginPw.equals(loginPwConfirm)) {
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
			} else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
			}
		}

		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
	
	
	
	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if ( index == -1 ) {
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

	private int getArticleIndexById(int id) {
		int i = 0;

		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}

		return -1;
	}

	private Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, "제목1", "aaaa", Util.getNowDateStr()));
		articles.add(new Article(2, "제목2", "bbbb", Util.getNowDateStr()));
		articles.add(new Article(3, "제목3", "cccc", Util.getNowDateStr()));
	}
}
