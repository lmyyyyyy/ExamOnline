package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.PaperItem;


public interface PaperItemService extends BaseService<PaperItem>{

	/**
	 * 获取所有的试卷条目
	 * @return
	 */
	public List<PaperItem> getAllPaperItem();

	/**
	 * 按照id删除试卷条目
	 * @param sid
	 */
	public void delete(Integer sid);
	
	/**
	 * 按照作者名字获取所有试卷条目
	 */
	public List<PaperItem> getAllPaperItem(String author);

	/**
	 * 按照id获取试卷条目
	 * @param sid
	 * @return
	 */
	public PaperItem getPaperItem(Integer sid);

	/**
	 * 保存更新试卷
	 * @param model
	 */
	public void saveOrUpdate(PaperItem model);

	/**
	 * 通过试卷号查询所有试题
	 * @param paperNumber
	 * @return
	 */
	public List<PaperItem> getPaperItem(String paperNumber);

	/**
	 * 根据试卷号和试题类型获取该类型的集合
	 * @param paperNumber
	 * @param string
	 * @return
	 */
	public List<PaperItem> getSingleChoice(String paperNumber, String type);

	/**
	 * 根据试卷号和试题类型获取该类型的集合
	 * @param paperNumber
	 * @param type
	 * @return
	 */
	public List<PaperItem> getMultipleChoice(String paperNumber,
			String type);

	/**
	 * 根据试卷号和试题类型获取该类型的集合
	 * @param paperNumber
	 * @param type
	 * @return
	 */
	public List<PaperItem> getBlank(String paperNumber, String type);

	/**
	 * 根据试卷号和试题类型获取该类型的集合
	 * @param paperNumber
	 * @param type
	 * @return
	 */
	public List<PaperItem> getJudgment(String paperNumber, String type);
	
	/**
	 * 根据试卷号和试题类型获取该类型的集合
	 * @param paperNumber
	 * @param type
	 * @return
	 */
	public List<PaperItem> getProgram(String paperNumber, String type);


}
