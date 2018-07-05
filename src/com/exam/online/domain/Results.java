package com.exam.online.domain;

import java.util.Date;

/**
 * 答完的学生试卷信息
 * @author Administrator
 */
public class Results {
	private Integer id;
	private String subjcet;
	private String snumber;
	private String sname;
	private String sclas;
	private String enumber;
	private String ename;
	private Integer singleScore;
	private	Integer	multipleScore;
	private Integer blankScore;
	private Integer judgmentScore;
	private Integer programScore;
	private String singleAnswer;
	private String multipleAnswer;
	private String blankAnswer;
	private String judgmentAnswer;
	private String programAnswer;
	private Integer totalScore;
	private Date regDate = new Date();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubjcet() {
		return subjcet;
	}
	public void setSubjcet(String subjcet) {
		this.subjcet = subjcet;
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
	public Integer getSingleScore() {
		return singleScore;
	}
	public void setSingleScore(Integer singleScore) {
		this.singleScore = singleScore;
	}
	public Integer getMultipleScore() {
		return multipleScore;
	}
	public void setMultipleScore(Integer multipleScore) {
		this.multipleScore = multipleScore;
	}
	public Integer getBlankScore() {
		return blankScore;
	}
	public void setBlankScore(Integer blankScore) {
		this.blankScore = blankScore;
	}
	public Integer getProgramScore() {
		return programScore;
	}
	public void setProgramScore(Integer programScore) {
		this.programScore = programScore;
	}
	public String getSingleAnswer() {
		return singleAnswer;
	}
	public void setSingleAnswer(String singleAnswer) {
		this.singleAnswer = singleAnswer;
	}
	public String getMultipleAnswer() {
		return multipleAnswer;
	}
	public void setMultipleAnswer(String multipleAnswer) {
		this.multipleAnswer = multipleAnswer;
	}
	public String getBlankAnswer() {
		return blankAnswer;
	}
	public void setBlankAnswer(String blankAnswer) {
		this.blankAnswer = blankAnswer;
	}
	public String getJudgmentAnswer() {
		return judgmentAnswer;
	}
	public void setJudgmentAnswer(String judgmentAnswer) {
		this.judgmentAnswer = judgmentAnswer;
	}
	public String getProgramAnswer() {
		return programAnswer;
	}
	public void setProgramAnswer(String programAnswer) {
		this.programAnswer = programAnswer;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getSclas() {
		return sclas;
	}
	public void setSclas(String sclas) {
		this.sclas = sclas;
	}
	public Integer getJudgmentScore() {
		return judgmentScore;
	}
	public void setJudgmentScore(Integer judgmentScore) {
		this.judgmentScore = judgmentScore;
	}
}
