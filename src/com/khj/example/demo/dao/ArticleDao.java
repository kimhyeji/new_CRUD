package com.khj.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.khj.example.demo.db.DBConnection;
import com.khj.example.demo.dto.Article;
import com.khj.example.demo.dto.ArticleReply;
import com.khj.example.demo.dto.Board;
import com.khj.example.demo.factory.Factory;

//Dao
public class ArticleDao {
	private DBConnection dbConnection;

	public ArticleDao() {
		dbConnection = Factory.getDBConnection();
	}

	public List<Article> getArticlesByBoardCode(String code) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT A.* "));
		sb.append(String.format("FROM `article` AS A "));
		sb.append(String.format("INNER JOIN `board` AS B "));
		sb.append(String.format("ON A.boardId = B.id "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND B.`code` = '%s' ", code));
		sb.append(String.format("ORDER BY A.id DESC "));

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public List<Board> getBoards() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `board` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("ORDER BY id DESC "));

		List<Board> boards = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			boards.add(new Board(row));
		}

		return boards;
	}

	public Board getBoardByCode(String code) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `board` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND `code` = '%s' ", code));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}

		return new Board(row);
	}

	public int saveBoard(Board board) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO board "));
		sb.append(String.format("SET regDate = '%s' ", board.getRegDate()));
		sb.append(String.format(", `code` = '%s' ", board.getCode()));
		sb.append(String.format(", `name` = '%s' ", board.getName()));

		return dbConnection.insert(sb.toString());
	}

	public int save(Article article) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO article "));
		sb.append(String.format("SET regDate = '%s' ", article.getRegDate()));
		sb.append(String.format(", `title` = '%s' ", article.getTitle()));
		sb.append(String.format(", `body` = '%s' ", article.getBody()));
		sb.append(String.format(", `boardId` = '%d' ", article.getBoardId()));

		return dbConnection.insert(sb.toString());
	}

	public Board getBoard(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `board` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("AND `id` = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}

		return new Board(row);
	}

	public List<Article> getArticles() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `article` "));
		sb.append(String.format("WHERE 1 "));
		sb.append(String.format("ORDER BY id DESC "));

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	public Article getArticle(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `article` "));
		sb.append(String.format("WHERE id = '%d' ", id));

		String sql = sb.toString();
		Map<String, Object> row = dbConnection.selectRow(sql);

		if (row.isEmpty()) {
			return null;
		}

		return new Article(row);
	}

	public Article getForPrintArticle(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT A.* "));
		sb.append(String.format("FROM `article` AS A "));
		sb.append(String.format("WHERE A.id = '%d' ", id));

		String sql = sb.toString();
		Map<String, Object> row = dbConnection.selectRow(sql);

		if (row.isEmpty()) {
			return null;
		}

		return new Article(row);
	}

	// 게시물 수정
	public void modify(int modiNum, String newTitle, String newBody) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `article` SET "));
		sb.append(String.format("title = '%s'", newTitle));
		sb.append(String.format(", `body` = '%s'", newBody));
		sb.append(String.format(" WHERE `id` = %d", modiNum));

		dbConnection.update(sb.toString());
	}

	// 게시물 삭제
	public void delete(int delNum) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM `article` "));
		sb.append(String.format(" WHERE `id` = %d", delNum));

		dbConnection.insert(sb.toString());
	}

	// 댓글 ----------------------------------------------------------------------
	public List<ArticleReply> getArticleRepliesByArticleId(int articleId) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `articleReply` "));
		sb.append(String.format("WHERE articleId = '%d' ", articleId));
		sb.append(String.format("ORDER BY id DESC "));

		List<ArticleReply> articleReplies = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			articleReplies.add(new ArticleReply(row));
		}

		return articleReplies;
	}

	public List<ArticleReply> getForPrintArticleRepliesByArticleId(int articleId) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT AR.* "));
		sb.append(String.format("FROM `articleReply` AS AR "));
		sb.append(String.format("WHERE AR.articleId = '%d' ", articleId));
		sb.append(String.format("ORDER BY AR.id DESC "));

		List<ArticleReply> articleReplies = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			articleReplies.add(new ArticleReply(row));
		}

		return articleReplies;
	}

	public List<Article> getForPrintArticlesByBoardCode(String code) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT A.* "));
		sb.append(String.format("FROM `article` AS A "));
		sb.append(String.format("INNER JOIN `board` AS B "));
		sb.append(String.format("ON A.boardId = B.id "));
		sb.append(String.format("WHERE B.code = '%s' ", code));
		sb.append(String.format("ORDER BY A.id DESC "));

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	// 번호에 해당하는 댓글 가져오기
	public ArticleReply getArticleReply(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `articleReply` "));
		sb.append(String.format("WHERE id = '%d' ", id));

		String sql = sb.toString();
		Map<String, Object> row = dbConnection.selectRow(sql);

		if (row.isEmpty()) {
			return null;
		}

		return new ArticleReply(row);
	}

	// 댓글 작성
	public int reply(ArticleReply articleReply) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO articleReply "));
		sb.append(String.format("SET regDate = '%s' ", articleReply.getRegDate()));
		sb.append(String.format(", `body` = '%s' ", articleReply.getBody()));
		sb.append(String.format(", `articleId` = '%d' ", articleReply.getArticleId()));

		return dbConnection.insert(sb.toString());
	}

	// 댓글 삭제
	public void replyDelete(int delNum) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM `articleReply` "));
		sb.append(String.format(" WHERE `id` = %d", delNum));

		dbConnection.insert(sb.toString());
	}

}
