package com.khj.example.demo.dto;

import java.util.Map;

public class Board extends Dto {
	private String name;
	private String code;

	public Board() {
	}

	public Board(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public Board(Map<String, Object> row) {
		super(row);
		this.code = (String) row.get("code");
		this.name = (String) row.get("name");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}