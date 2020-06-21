package com.khj.example.demo.controller;

import com.khj.example.demo.factory.Factory;
import com.khj.example.demo.service.BuildService;

public class BuildController extends Controller {
	private BuildService buildService;

	public BuildController() {
		buildService = Factory.getBuildService();
	}

	@Override
	public void doAction(Request reqeust) {
		if (reqeust.getActionName().equals("site")) {
			actionSite(reqeust);
		}
	}

	private void actionSite(Request reqeust) {
		buildService.buildSite();
	}
}
