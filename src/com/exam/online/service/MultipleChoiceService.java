package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.MultipleChoice;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface MultipleChoiceService extends BaseService<MultipleChoice>{

	/**
	 * 获取所有的多选题
	 * @return
	 */
	public List<MultipleChoice> getAllMultipleChoice();

	/**
	 * 按id删除多选
	 * @param sid
	 */
	public void delete(Integer sid);

	/**
	 * 按id获取多选信息
	 * @param sid
	 * @return
	 */
	public MultipleChoice getSingleChoice(Integer sid);

	/**
	 *保存或更新多选
	 * @param model
	 */
	public void saveOrUpdate(MultipleChoice model);
	
	/**
	 * 根据学科和难度获取数据库中该类型题的数量
	 * @param subject
	 * @param difficult
	 * @return
	 */
	public int getQuestionCount(String subject, Integer difficult);

	/**
	 * 根据组卷风格和难度和前台传进来的题的数量抽取试题
	 * @param multipleCount
	 * @param style
	 * @param difficult
	 * @return
	 */
	public List<MultipleChoice> extract(
			Integer multipleCount, String style, Integer difficult);
	
	/**
	 * 根据试卷中的题号获取多选题
	 * @param number
	 * @return
	 */
	public MultipleChoice getMultipleChoice(String number);

	/**
	 * 验证题号是否存在
	 * @param number
	 * @return
	 */
	public boolean isRegisted(String number);
	
	/**
	 * 分页获取所有的多选题
	 * @param page
	 * @return
	 */
	public Result getAllMultipleChoiceByPage(Page page);
}
