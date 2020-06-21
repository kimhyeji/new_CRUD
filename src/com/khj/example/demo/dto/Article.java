package com.khj.example.demo.dto;

import java.util.Map;

public class Article extends Dto {
	private int boardId;
	private String title;
	private String body;

	public Article() {

	}

	public Article(int boardId, String title, String body) {
		this.boardId = boardId;
		this.title = title;
		this.body = body;
	}

	public Article(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.boardId = (int) row.get("boardId");
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
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

	@Override
	public String toString() {
		return "Article [boardId=" + boardId + ", memberId=" + ", title=" + title + ", body=" + body
				+ ", getId()=" + getId() + ", getRegDate()=" + getRegDate() + "]";
	}

}