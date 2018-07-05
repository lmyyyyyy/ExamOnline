package com.exam.online.domain;

import java.util.Date;

/**
 * 试卷
 * @author Administrator
 */
public class Paper {
	private Integer id;
	private String number;
	private String name;
	private Integer singleCount;
	private Integer multipleCount;
	private Integer blankCount;
	private Integer judgmentCount;
	private Integer programCount;
	private String subject;
	private String author;
	private Date regDate = new Date();
	private Integer difficult;
	private Integer status;
	private Integer score;
	private String style;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSingleCount() {
		return singleCount;
	}
	public void setSingleCount(Integer singleCount) {
		this.singleCount = singleCount;
	}
	public Integer getMultipleCount() {
		return multipleCount;
	}
	public void setMultipleCount(Integer multipleCount) {
		this.multipleCount = multipleCount;
	}
	public Integer getBlankCount() {
		return blankCount;
	}
	public void setBlankCount(Integer blankCount) {
		this.blankCount = blankCount;
	}
	public Integer getJudgmentCount() {
		return judgmentCount;
	}
	public void setJudgmentCount(Integer judgmentCount) {
		this.judgmentCount = judgmentCount;
	}
	public Integer getProgramCount() {
		return programCount;
	}
	public void setProgramCount(Integer programCount) {
		this.programCount = programCount;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}
