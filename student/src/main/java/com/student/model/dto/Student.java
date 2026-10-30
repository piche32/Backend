package com.student.model.dto;

public class Student {
	private String userid;
	private String userpw;
	private String userName;
	private int score;
	
	public Student() { }
	public Student(String userid, String userpw, String userName, int score) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.userName = userName;
		this.score = score;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "student [userId=" + userid + ", userPw=" + userpw + ", userName=" + userName + ", score=" + score + "]";
	}
	
	
}
