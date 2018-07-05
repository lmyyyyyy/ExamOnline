package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;

public interface TeacherService extends BaseService<Teacher>{
	/**
	 *判断学号是否占用 
	 */
	public boolean isRegisted(String sId);
	/**
	 * 校验登录信息
	 */
	public Teacher validateLoginInfo(String sId, String md5);
	
	/**
	 * 获取所有教师的信息
	 */
	public List<Teacher> getAllTeacher();
	
	/**
	 * 按id删除教师
	 */
	public void deleteTeacher(Integer sid);
	
	/**
	 * 按id获取teacher
	 */
	public Teacher getTeacher(Integer sid);
	
	/**
	 * 保存更新教师信息
	 */
	public void saveOrUpdateTea(Teacher model);
	
	/**
	 * 分页获取所有的教师
	 * @param page
	 * @return
	 */
	public Result getAllTeacherByPage(Page page);
}
