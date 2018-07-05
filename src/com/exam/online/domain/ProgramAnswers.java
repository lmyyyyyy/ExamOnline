package com.exam.online.domain;

import java.util.Date;

public class ProgramAnswers {
	private Integer id;
	private String subject;
	private String snumber;
	private String sname;
	private String sclas;
	private String enumber;
	private String ename;
	private Integer escore;
	private String first;
	private String second;
	private String third;
	private String forth;
	private String fifth;
	private Integer score;
	private Integer status;
	private Date regDate = new Date();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSclas() {
		return sclas;
	}
	public void setSclas(String sclas) {
		this.sclas = sclas;
	}
	public String getEnumber() {
		return enumber;
	}
	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThird() {
		return third;
	}
	public void setThird(String third) {
		this.third = third;
	}
	public String getForth() {
		return forth;
	}
	public void setForth(String forth) {
		this.forth = forth;
	}
	public String getFifth() {
		return fifth;
	}
	public void setFifth(String fifth) {
		this.fifth = fifth;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getEscore() {
		return escore;
	}
	public void setEscore(Integer escore) {
		this.escore = escore;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
