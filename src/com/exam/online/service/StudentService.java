package com.exam.online.service;

import java.util.List;

import com.exam.online.domain.Student;
import com.exam.online.page.Page;
import com.exam.online.page.Result;



public interface StudentService extends BaseService<Student>{
	/**
	 *判断学号是否占用 
	 */
	public boolean isRegisted(String sId);
	/**
	 * 校验登录信息
	 */
	public Student validateLoginInfo(String sId, String md5);
	
	/**
	 * 获取所有学生的信息
	 */
	public List<Student> getAllStudent();
	
	/**
	 * 按id删除学生
	 */
	public void deleteStudent(Integer sid);
	
	/**
	 *按id获取学生信息 
	 */
	public Student getStudent(Integer sid);
	
	/**
	 * 保存/更新学生
	 */
	public void saveOrUpdateStu(Student model);
	
	/**
	 * 根据具体类型查询学生
	 * @param type
	 * @param query
	 * @return
	 */
	public List<Student> getAllStudent(String type, String query);
	
	/**
	 * 根据具体类型查询学生
	 * @param type
	 * @param query
	 * @return
	 */
	public Result getAllStudentByPage(Page page, String type, String query);
	
	/**
	 * 分页获取所有的学生
	 * @param page
	 * @return
	 */
	public Result getAllStudentByPage(Page page);
}
