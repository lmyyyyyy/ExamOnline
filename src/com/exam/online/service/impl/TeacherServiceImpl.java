package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.TeacherService;
import com.exam.online.util.ValidateUtil;

@Service("teacherService")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements 
					TeacherService{
	
	@Resource(name="teacherDao")
	public void setDao(BaseDao<Teacher> dao) {
		super.setDao(dao);
	}

	public boolean isRegisted(String number) {
		String hql = "from Teacher t where t.number = ?";
		List<Teacher> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list);
	}

	public Teacher validateLoginInfo(String number, String password) {
		String hql = "from Teacher t where t.number = ? and t.password = ?";
		List<Teacher> list = this.findEntityByHQL(hql, number, password);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}

	public List<Teacher> getAllTeacher() {
		String hql = "from Teacher";
		return this.findEntityByHQL(hql);
	}

	public void deleteTeacher(Integer sid) {
		String hql = "delete from Teacher where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public Teacher getTeacher(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdateTea(Teacher model) {
		this.saveOrUpdateEntity(model);
	}

	public Result getAllTeacherByPage(Page page) {
		String hql = "from Teacher";
		List<Teacher> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Teacher> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

}
