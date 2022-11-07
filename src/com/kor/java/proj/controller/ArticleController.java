package com.kor.java.proj.controller;

import java.util.List;
import java.util.Scanner;

import com.kor.java.proj.dto.Article;
import com.kor.java.proj.util.Util;

public class ArticleController {

	private Scanner sc;
	private List<Article> articles;
	
	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.articles = articles;
	}
	
	public void dowrite() {
		int id = articles.size() + 1;
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		String WriteTime = Util.getNowDateStr();
		
		Article article = new Article(id, title, body, WriteTime);
		articles.add(article);
		
		System.out.printf("%d번 글이 생성되었습니다.\n", id);	
	}
	
	public void showlist() {
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}

		System.out.println(" 번호 |  조회 | 제목");
		for (int i = articles.size() - 1; i >= 0; i--) {
			Article article = articles.get(i);

			System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);
		}
	}

	public void showdetail(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]); // "1" -> 1

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.time);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);		
	}
	
	public void dodelete(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]); // "1" -> 1
		
		int foundIndex = getArticleIndexById(id);
		
		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		articles.remove(foundIndex);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);		
	}
	
	public void domodify(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]); // "1" -> 1
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		foundArticle.title = title;
		foundArticle.body = body;
		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);	
	}
	

	private Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
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




}
