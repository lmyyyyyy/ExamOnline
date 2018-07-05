package com.exam.online.domain;

import java.util.Date;

/**
 * 多选题
 * @author Administrator
 */
public class MultipleChoice {
	private Integer id;
	private String number;
	private String question;
	private String choicea;
	private String choiceb;
	private String choicec;
	private String choiced;
	private String choicee;
	private String choicef;
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
	public String getChoicea() {
		return choicea;
	}
	public void setChoicea(String choicea) {
		this.choicea = choicea;
	}
	public String getChoiceb() {
		return choiceb;
	}
	public void setChoiceb(String choiceb) {
		this.choiceb = choiceb;
	}
	public String getChoicec() {
		return choicec;
	}
	public void setChoicec(String choicec) {
		this.choicec = choicec;
	}
	public String getChoiced() {
		return choiced;
	}
	public void setChoiced(String choiced) {
		this.choiced = choiced;
	}
	public String getChoicee() {
		return choicee;
	}
	public void setChoicee(String choicee) {
		this.choicee = choicee;
	}
	public String getChoicef() {
		return choicef;
	}
	public void setChoicef(String choicef) {
		this.choicef = choicef;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
