package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Program;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface ProgramService extends BaseService<Program>{

	/**
	 * 获取所有的编程
	 * @return
	 */
	public List<Program> getAllProgram();

	/**
	 * 按照id删除编程
	 * @param sid
	 */
	public void delete(Integer sid);

	/**
	 * 按照id获取编程
	 * @param sid
	 * @return
	 */
	public Program getProgram(Integer sid);

	/**
	 * 保存更新编程
	 * @param model
	 */
	public void saveOrUpdate(Program model);
	
	/**
	 * 根据学科和难度获取数据库中该类型题的数量
	 * @param subject
	 * @param difficult
	 * @return
	 */
	public int getQuestionCount(String subject, Integer difficult);

	/**
	 * 根据组卷风格和难度和前台传进来的题的数量抽取试题
	 * @param programCount
	 * @param style
	 * @param difficult
	 * @return
	 */
	public List<Program> extract(
			Integer programCount, String style, Integer difficult);

	/**
	 * 根据试卷中的题号获取编程题
	 * @param number
	 * @return
	 */
	public Program getProgram(String number);

	/**
	 * 验证题号是否存在
	 * @param number
	 * @return
	 */
	public boolean isRegisted(String number);
	
	/**
	 * 分页获取所有的编程题
	 * @param page
	 * @return
	 */
	public Result getAllProgramByPage(Page page);
}
