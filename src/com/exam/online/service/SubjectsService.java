package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Subjects;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface SubjectsService extends BaseService<Subjects>{

	/**
	 * 获取所有的科目
	 * @return
	 */
	public List<Subjects> getAllSubjects();

	/**
	 * 删除科目
	 * @param sid
	 */
	public void delete(Integer sid);

	/**
	 * 按照id获取科目信息
	 * @param sid
	 * @return
	 */
	public Subjects getSubjects(Integer sid);

	/**
	 * 保存或更新科目
	 * @param model
	 */
	public void saveOrUpdate(Subjects model);
	
	/**
	 * 分页获取所有的科目
	 * @param page
	 * @return
	 */
	public Result getAllSubjectsByPage(Page page);

}
