package com.KoreaIT.java.AM.dto;

public class Article extends Dto {

	private String updateDate;
	private Member writer;

	private String title;
	private String body;

	private int hit;

	public Article(int id, String regDate, String updateDate, String title, String body, Member writer) {
		this(id, regDate, updateDate, title, body, 0, writer);
		this.writer = writer;
	}

	public Article(int id, String regDate, String updateDate, String title, String body, int hit, Member writer) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.hit = hit;
		this.writer = writer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Member getWriter() {
		return writer;
	}

	public void setWriter(Member writer) {
		this.writer = writer;
	}
}