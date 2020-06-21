package com.khj.example.demo.dto;

import java.util.HashMap;
import java.util.Map;

import com.khj.example.demo.util.Util;

// DTO
public abstract class Dto {
	private int id;
	private String regDate;
	private Map<String, Object> extra;

	public Dto() {
		this(0);
	}

	public Dto(int id) {
		this(id, Util.getNowDateStr());
	}

	public Dto(int id, String regDate) {
		this(id, regDate, new HashMap<>());
	}

	public Dto(int id, String regDate, Map<String, Object> extra) {
		this.id = id;
		this.regDate = regDate;
		this.extra = extra;
	}

	public Dto(Map<String, Object> row) {
		this((int) row.get("id"), (String) row.get("regDate"));

		for (String key : row.keySet()) {
			if (key.startsWith("extra__")) {
				String extraKey = key.replace("extra__", "");
				Object extraValue = row.get(key);
				extra.put(extraKey, extraValue);
			}
		}
	}

	public Map<String, Object> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
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
}