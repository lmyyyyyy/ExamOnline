package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Judgment;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface JudgmentService extends BaseService<Judgment>{

	/**
	 * 获取所有的判断
	 * @return
	 */
	public List<Judgment> getAllJudgment();

	/**
	 * 按照id删除判断
	 * @param sid
	 */
	public void delete(Integer sid);

	/**
	 * 按照id获取判断
	 * @param sid
	 * @return
	 */
	public Judgment getJudgment(Integer sid);

	/**
	 * 保存更新判断
	 * @param model
	 */
	public void saveOrUpdate(Judgment model);
	
	/**
	 * 根据学科和难度获取数据库中该类型题的数量
	 * @param subject
	 * @param difficult
	 * @return
	 */
	public int getQuestionCount(String subject, Integer difficult);

	/**
	 * 根据组卷风格和难度和前台传进来的题的数量抽取试题
	 * @param judgmentCount
	 * @param style
	 * @param difficult
	 * @return
	 */
	public List<Judgment> extract(
			Integer judgmentCount, String style, Integer difficult);
	
	/**
	 * 根据试卷中的题号获取判断题
	 * @param number
	 * @return
	 */
	public Judgment getJudgment(String number);

	/**
	 * 验证题号是否存在
	 * @param number
	 * @return
	 */
	public boolean isRegisted(String number);
	
	/**
	 * 分页获取所有的判断题
	 * @param page
	 * @return
	 */
	public Result getAllJudgmentByPage(Page page);
	
}
