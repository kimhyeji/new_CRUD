package com.khj.example.demo.dto;

import java.util.Map;

public class ArticleReply extends Dto {
	private int articleId;
	private String body;

	public ArticleReply() {

	}

	public ArticleReply(Map<String, Object> row) {
		super(row);
		this.body = (String) row.get("body");
		this.articleId = (int) row.get("articleId");
	}
	
	public ArticleReply(int articleId, String body) {
		this.articleId = articleId;
		this.body = body;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}