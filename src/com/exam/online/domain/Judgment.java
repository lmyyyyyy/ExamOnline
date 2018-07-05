package com.exam.online.domain;

import java.util.Date;

/**
 * 判断题
 * @author Administrator
 */
public class Judgment {
	private Integer id;
	private String number;
	private String question;
	//T-正确  F-错误
	private String answer;
	private String subject;
	private String author;
	private Date regDate = new Date();
	private Integer difficult;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getDifficult() {
		return difficult;
	}
	public void setDifficult(Integer difficult) {
		this.difficult = difficult;
	}
}
