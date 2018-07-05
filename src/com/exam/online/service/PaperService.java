package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Paper;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface PaperService extends BaseService<Paper>{

	/**
	 * 获取所有的试卷
	 * @return
	 */
	public List<Paper> getAllPaper();

	/**
	 * 按照id删除试卷
	 * @param sid
	 */
	public void delete(Integer sid);
	
	/**
	 * 按照作者名字获取所有试卷
	 */
	public List<Paper> getAllPaper(String author);

	/**
	 * 按照id获取试卷
	 * @param sid
	 * @return
	 */
	public Paper getPaper(Integer sid);

	/**
	 * 保存更新试卷
	 * @param model
	 */
	public void saveOrUpdate(Paper model);

	/**
	 * 获取所有当前正在考试的试卷
	 * @return
	 */
	public List<Paper> getCurrentPaper();

	/**
	 * 验证试卷号是否存在
	 * @param number
	 * @return
	 */
	public boolean isRegisted(String number);

	/**
	 * 根据去除传过来的试卷号获取剩余的所有试卷
	 * @param enumberList
	 * @return
	 */
	public List<Paper> getCurrentPaper(List<String> enumberList);


	/**
	 * 分页获取所有的试卷
	 * @param page
	 * @return
	 */
	public Result getAllPaperByPage(Page page);
}
