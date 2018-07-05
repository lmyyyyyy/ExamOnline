package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Student;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.StudentService;
import com.exam.online.util.ValidateUtil;

@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements 
					StudentService{
	
	@Resource(name="studentDao")
	public void setDao(BaseDao<Student> dao) {
		super.setDao(dao);
	}

	/**
	 * 验证学号是否存在
	 */
	public boolean isRegisted(String number) {
		String hql = "from Student s where s.number = ?";//查找学号中是否有number
		List<Student> list = this.findEntityByHQL(hql, number);//根据hql语句，和number参数获取符合条件的Student的list
		return ValidateUtil.isValid(list);//验证当前list是否为空
	}

	/**
	 * 验证旧密码是否正确
	 */
	public Student validateLoginInfo(String number, String password) {
		String hql = "from Student s where s.number = ? and s.password = ?";
		List<Student> list = this.findEntityByHQL(hql, number, password);//根据当前学生的学号和加密后的密码查询数据库
		return ValidateUtil.isValid(list)?list.get(0):null;
	}

	/**
	 * 获取所有学生
	 */
	public List<Student> getAllStudent() {
		String hql = "from Student s";
		return this.findEntityByHQL(hql);
	}

	public void deleteStudent(Integer sid) {
		String hql = "delete from Student where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public Student getStudent(Integer sid) {
		return this.getEntity(sid);
	}
	/**
	 * 保存更新学生
	 */
	public void saveOrUpdateStu(Student model) {
		this.saveOrUpdateEntity(model);;
	}

	public List<Student> getAllStudent(String type, String query) {
		String hql = "from Student";
		if (query == null || "".equals(query)) {
			return this.findEntityByHQL(hql);
		} else {
			if (type.equals("number")) {
				hql = "from Student s where s.number like '%"+query+"%' order by regdate desc";
			} else if (type.equals("name")){
				hql = "from Student s where s.name like '%"+query+"%' order by regdate desc";
			} else if (type.equals("syear")) {
				hql = "from Student s where s.syear like '%"+query+"%' order by regdate desc";
			} else if (type.equals("major")) {
				hql = "from Student s where s.major like '%"+query+"%' order by regdate desc";
			} else if (type.equals("clas")) {
				hql = "from Student s where s.clas like '%"+query+"%' order by regdate desc";
			} else if (type.equals("status")) {
				hql = "from Student s where s.status like '%"+query+"%' order by regdate desc";
			}
		}
		return this.findEntityByHQL(hql);
	}

	public Result getAllStudentByPage(Page page) {
		String hql = "from Student";
		List<Student> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Student> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

	public Result getAllStudentByPage(Page page, String type, String query) {
		String hql = "from Student order by regdate desc";
		if (query == null || "".equals(query)) {
			hql = "from Student order by regdate desc";
		} else {
			if (type.equals("number")) {
				hql = "from Student s where s.number like '%"+query+"%' order by regdate desc";
			} else if (type.equals("name")){
				hql = "from Student s where s.name like '%"+query+"%' order by regdate desc";
			} else if (type.equals("syear")) {
				hql = "from Student s where s.syear like '%"+query+"%' order by regdate desc";
			} else if (type.equals("major")) {
				hql = "from Student s where s.major like '%"+query+"%' order by regdate desc";
			} else if (type.equals("clas")) {
				hql = "from Student s where s.clas like '%"+query+"%' order by regdate desc";
			} else if (type.equals("status")) {
				hql = "from Student s where s.status like '%"+query+"%' order by regdate desc";
			}
		}
		List<Student> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Student> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

}
