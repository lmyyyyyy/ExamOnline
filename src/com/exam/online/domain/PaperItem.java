package com.exam.online.domain;

import java.util.Date;

/**
 * 试卷的试题条目
 * @author Administrator
 */
public class PaperItem {
	private Integer id;
	private Integer score;
	private String enumber;
	private String qnumber;
	private String type;
	private Date regDate = new Date();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getEnumber() {
		return enumber;
	}
	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}
	public String getQnumber() {
		return qnumber;
	}
	public void setQnumber(String qnumber) {
		this.qnumber = qnumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
