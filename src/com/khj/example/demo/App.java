package com.khj.example.demo;

import java.util.HashMap;
import java.util.Map;

import com.khj.example.demo.controller.ArticleController;
import com.khj.example.demo.controller.BuildController;
import com.khj.example.demo.controller.Controller;
import com.khj.example.demo.controller.Request;
import com.khj.example.demo.db.DBConnection;
import com.khj.example.demo.factory.Factory;

// App
public class App {
	private Map<String, Controller> controllers;

	// 컨트롤러 만들고 한곳에 정리
	// 나중에 컨트롤러 이름으로 쉽게 찾아쓸 수 있게 하려고 Map 사용
	public void initControllers() {
		controllers = new HashMap<>();
		controllers.put("build", new BuildController());
		controllers.put("article", new ArticleController());
	}

	public App() {
		// 컨트롤러 등록
		initControllers();

		// DB 정보세팅
		DBConnection.DB_NAME = "site33";
		DBConnection.DB_USER = "root";
		DBConnection.DB_PASSWORD = "hj1234";
		DBConnection.DB_PORT = 3306;

		Factory.getDBConnection().connect();


		// 공지사항 게시판 생성
		Factory.getArticleService().makeBoardIfNotExists("공지사항", "notice");
		// 자유 게시판 생성
		Factory.getArticleService().makeBoardIfNotExists("자유", "free");

		// 현재 게시판을 1번 게시판으로 선택
		Factory.getSession().setCurrentBoard(Factory.getArticleService().getBoard(1));
	}

	public void start() {

		while (true) {
			System.out.printf("명령어 : ");
			String command = Factory.getScanner().nextLine().trim();

			if (command.length() == 0) {
				continue;
			} else if (command.equals("exit")) {
				break;
			}

			Request reqeust = new Request(command);

			if (reqeust.isValidRequest() == false) {
				continue;
			}

			if (controllers.containsKey(reqeust.getControllerName()) == false) {
				continue;
			}

			controllers.get(reqeust.getControllerName()).doAction(reqeust);
		}

		Factory.getDBConnection().close();
		Factory.getScanner().close();
	}
}