package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Blank;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface BlankService extends BaseService<Blank>{

	/**
	 * 获取所有的填空
	 * @return
	 */
	public List<Blank> getAllBlank();

	/**
	 * 按照id删除填空
	 * @param sid
	 */
	public void delete(Integer sid);

	/**
	 * 按照id获取填空
	 * @param sid
	 * @return
	 */
	public Blank getBlank(Integer sid);

	/**
	 * 保存更新填空
	 * @param model
	 */
	public void saveOrUpdate(Blank model);
	
	/**
	 * 根据学科和难度获取数据库中该类型题的数量
	 * @param subject
	 * @param difficult
	 * @return
	 */
	public int getQuestionCount(String subject, Integer difficult);

	/**
	 * 根据组卷风格和难度和前台传进来的题的数量抽取试题
	 * @param blankCount
	 * @param style
	 * @param difficult
	 * @return
	 */
	public List<Blank> extract(
			Integer blankCount, String style, Integer difficult);
	
	/**
	 * 根据试卷中的题号获取填空题
	 * @param number
	 * @return
	 */
	public Blank getBlank(String number);

	/**
	 * 验证题号是否存在
	 * @param number
	 * @return
	 */
	public boolean isRegisted(String number);
	
	/**
	 * 分页获取所有的填空题
	 * @param page
	 * @return
	 */
	public Result getAllBlankByPage(Page page);
}
