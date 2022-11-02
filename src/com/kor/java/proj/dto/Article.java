package com.kor.java.proj.dto;

public class Article {
	public int id;
	public String title;
	public String body;
	public String time;
	public int hit;

	public Article(int id, String title, String body, String time) {
		this(id, title, body, time, 0);
	}

	public Article(int id, String title, String body, String time, int hit) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.time = time;
		this.hit = hit;
	}

	public void increaseHit() {
		hit++;
	}
}