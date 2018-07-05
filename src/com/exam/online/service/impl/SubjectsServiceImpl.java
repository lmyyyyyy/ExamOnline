package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Subjects;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.SubjectsService;

@Service("subjectsService")
public class SubjectsServiceImpl extends BaseServiceImpl<Subjects> 
				implements SubjectsService{
	
	@Resource(name="subjectsDao")
	public void setDao(BaseDao<Subjects> dao) {
		super.setDao(dao);
	}

	public List<Subjects> getAllSubjects() {
		String hql = "from Subjects";
		return this.findEntityByHQL(hql);
	}

	public void delete(Integer sid) {
		String hql = "delete from Subjects where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public Subjects getSubjects(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(Subjects model) {
		this.saveOrUpdateEntity(model);
	}

	public Result getAllSubjectsByPage(Page page) {
		String hql = "from Subjects";
		List<Subjects> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Subjects> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}
}
