package com.khj.example.demo.factory;

import java.util.Scanner;

import com.khj.example.demo.controller.Session;
import com.khj.example.demo.dao.ArticleDao;
import com.khj.example.demo.db.DBConnection;
import com.khj.example.demo.service.ArticleService;
import com.khj.example.demo.service.BuildService;

public class Factory {
	private static Session session;
	private static DBConnection dbConnection;
	private static BuildService buildService;
	private static ArticleService articleService;
	private static ArticleDao articleDao;
	private static Scanner scanner;
	
	public static DBConnection getDBConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}

		return dbConnection;
	}

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}

		return session;
	}

	public static Scanner getScanner() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}

		return scanner;
	}

	public static ArticleService getArticleService() {
		if (articleService == null) {
			articleService = new ArticleService();
		}

		return articleService;
	}

	public static ArticleDao getArticleDao() {
		if (articleDao == null) {
			articleDao = new ArticleDao();
		}

		return articleDao;
	}

	public static BuildService getBuildService() {
		if (buildService == null) {
			buildService = new BuildService();
		}

		return buildService;
	}
}
