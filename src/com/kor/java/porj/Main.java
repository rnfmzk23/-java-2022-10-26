package com.kor.java.porj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int ArticleNum = 0;

		List<Article> article = new ArrayList<>();

		System.out.println("== 프로그램 시작 ==");

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

			else if (command.equals("article list")) {
				if (ArticleNum == 0) {
					System.out.println("게시글이 없습니다.");
				} else {
					System.out.printf("%d개의 게시글이 있습니다.\n", ArticleNum);
					System.out.printf("     번호     |     제목     \n");
					for(int i = ArticleNum; i > 0; i--) {						
						System.out.printf("      %d      |     %s     \n", i, article.get(i-1).title);
					}
				}
			}

			else if (command.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				article.add(new Article(title, body));
				ArticleNum++;
				System.out.printf("%d번 글이 생성되었습니다.\n", ArticleNum);
			}

			else if (command.equals("article read")) {
				System.out.printf("몇번 글을 읽으시겠습니까? ");
				int ReadNum = sc.nextInt();
				sc.nextLine();

				if (ReadNum > ArticleNum) {
					System.out.printf("해당 글은 없습니다.\n");
					System.out.printf("글이 %d번까지만 생성되었습니다.\n", ArticleNum);
				} else if (ReadNum <= 0) {
					System.out.printf("해당 글은 없습니다.\n");
					System.out.printf("1이상 입력해주십시오.\n");
				} else {
					System.out.printf("%d번글을 읽습니다.\n", ReadNum);
					System.out.printf("제목 : %s\n", article.get(ReadNum-1).title);
					System.out.printf("내용 : %s\n", article.get(ReadNum-1).body);
				}

			}

			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
			}

		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
}

class Article {
	String title;
	String body;

	Article(String title, String body) {
		this.title = title;
		this.body = body;
	}

}
