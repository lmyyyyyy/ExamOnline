package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.ProgramAnswers;

public interface ProgramAnswersService extends BaseService<ProgramAnswers>{

	/**
	 * 根据试卷号和学生学号获取当前编程记录的id
	 * @param enumber
	 * @param snumber
	 * @return
	 */
	ProgramAnswers getProgramAnswers(String enumber, String snumber);

	/**
	 * 获取所有的学生编程答案
	 * @return
	 */
	List<ProgramAnswers> getAllPAnswers();

	/**
	 * 修改状态
	 */
	void changeStatus();


}
