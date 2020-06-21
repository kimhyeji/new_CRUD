package com.khj.example.demo.service;

import java.util.List;

import com.khj.example.demo.dao.ArticleDao;
import com.khj.example.demo.dto.Article;
import com.khj.example.demo.dto.ArticleReply;
import com.khj.example.demo.dto.Board;
import com.khj.example.demo.factory.Factory;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Factory.getArticleDao();
	}

	public List<Article> getArticlesByBoardCode(String code) {
		return articleDao.getArticlesByBoardCode(code);
	}

	public List<Board> getBoards() {
		return articleDao.getBoards();
	}

	public int makeBoard(String name, String code) {
		Board oldBoard = articleDao.getBoardByCode(code);

		if (oldBoard != null) {
			return -1;
		}

		Board board = new Board(name, code);
		return articleDao.saveBoard(board);
	}

	public Board getBoard(int id) {
		return articleDao.getBoard(id);
	}

	public int write(int boardId, String title, String body) {
		Article article = new Article(boardId, title, body);
		return articleDao.save(article);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void makeBoardIfNotExists(String name, String code) {
		Board board = articleDao.getBoardByCode(code);

		if (board == null) {
			makeBoard(name, code);
		}
	}

	public Board getBoardByCode(String boardCode) {
		return articleDao.getBoardByCode(boardCode);
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}
	
	// 게시물 수정
	public void modify(int modiNum, String newTitle, String newBody) {
		articleDao.modify(modiNum, newTitle, newBody);
	}

	// 게시물 삭제
	public void delete(int delNum) {
		articleDao.delete(delNum);
	}

	// 댓글 ------------------------------------
	public List<ArticleReply> getArticleRepliesByArticleId(int articleId) {
		return articleDao.getArticleRepliesByArticleId(articleId);
	}

	public List<ArticleReply> getForPrintArticleRepliesByArticleId(int articleId) {
		return articleDao.getForPrintArticleRepliesByArticleId(articleId);
	}

	public List<Article> getForPrintArticlesByBoardCode(String code) {
		return articleDao.getForPrintArticlesByBoardCode(code);
	}
	
	public ArticleReply getArticleReply(int id) {
		return articleDao.getArticleReply(id);
	}

	// 댓글 작성
	public int reply(int articleId, String replyText) {
		ArticleReply articleReply = new ArticleReply(articleId, replyText);
		return articleDao.reply(articleReply);
	}

	// 댓글 삭제
	public void replyDelete(int delNum) {
		articleDao.replyDelete(delNum);
	}

}