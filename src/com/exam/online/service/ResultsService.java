package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Results;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface ResultsService extends BaseService<Results>{

	/**
	 * 根据当前学生学号获取该学生的成绩
	 * @param snumber
	 * @return
	 */
	List<Results> getResults(String snumber);
	
	/**
	 * 根据当前学生学号获取该学生的成绩
	 * @param snumber
	 * @return
	 */
	Result getResultsByPage(Page page, String snumber);

	/**
	 * 验证该学生是否答过该试卷
	 * @param enumber
	 * @param snumber
	 * @return
	 */
	Results isRegisted(String enumber, String snumber);

	/**
	 * 获取所有学生的成绩
	 * @return
	 */
	List<Results> getAllResults();
	
	

	/**
	 * 根据类型查询成绩
	 * @param type
	 * @param query
	 * @return
	 */
	List<Results> getAllResults(String type, String query);

	/**
	 * 根据当前学生的学号查询成绩表中所有成绩
	 * @param number
	 * @return
	 */
	List<Results> getAllResults(String number);
	
	/**
	 * 根据具体类型分页查询成绩
	 * @param type
	 * @param query
	 * @return
	 */
	public Result getAllResultsByPage(Page page, String type, String query);
	
	/**
	 * 分页获取所有的成绩
	 * @param page
	 * @return
	 */
	public Result getAllResultsByPage(Page page);

	/**
	 * 根据试卷号获取成绩表中所有带该试卷号的成绩
	 * @param number
	 * @return
	 */
	List<Results> getCurrentResults(String enumber);

}
