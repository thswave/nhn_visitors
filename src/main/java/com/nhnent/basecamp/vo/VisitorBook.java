package com.nhnent.basecamp.vo;

import com.google.gson.Gson;


public class VisitorBook{
	
	private int id;
	private String name;
	private String password;
	private String content;
	private String email;
	private String created_at;
	private String updated_at;
	
	public VisitorBook(){}
	
	public VisitorBook(int id, String name, String password, String content,
			String email, String created_at, String updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.content = content;
		this.email = email;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	

}
