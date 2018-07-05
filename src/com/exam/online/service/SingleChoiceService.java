package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.SingleChoice;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface SingleChoiceService extends BaseService<SingleChoice>{

	/**
	 * 获取所有的单选题
	 * @return
	 */
	public List<SingleChoice> getAllSingleChoice();

	/**
	 * 按照id删除单选
	 * @param sid
	 */
	public void delete(Integer sid);

	/**
	 * 按照id获取单选
	 * @param sid
	 * @return
	 */
	public SingleChoice getSingleChoice(Integer sid);

	/**
	 * 保存更新单选
	 * @param model
	 */
	public void saveOrUpdate(SingleChoice model);

	/**
	 * 根据学科和难度获取数据库中该类型题的数量
	 * @param subject
	 * @param difficult
	 * @return
	 */
	public int getQuestionCount(String subject, Integer difficult);

	/**
	 * 根据组卷风格和难度和前台传进来的题的数量抽取试题
	 * @param singleCount
	 * @param style
	 * @param difficult
	 * @return
	 */
	public List<SingleChoice> extract(
			Integer singleCount, String style, Integer difficult);
	
	/**
	 * 根据试卷中的题号获取单选题
	 * @param number
	 * @return
	 */
	public SingleChoice getSingleChoice(String number);

	/**
	 * 验证题号是否存在
	 * @param number
	 * @return
	 */
	public boolean isRegisted(String number);

	/**
	 * 分页获取所有的单选题
	 * @param page
	 * @return
	 */
	public Result getAllSingleChoiceByPage(Page page);
}
