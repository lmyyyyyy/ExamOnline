package com.exam.online.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Paper;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.PaperService;
import com.exam.online.util.ValidateUtil;

@Service("paperService")
public class PaperServiceImpl extends BaseServiceImpl<Paper>
				implements PaperService{

	@Resource(name="paperDao")
	public void setDao(BaseDao<Paper> dao) {
		super.setDao(dao);
	}

	public List<Paper> getAllPaper() {
		String hql = "from Paper";
		return this.findEntityByHQL(hql);
	}

	public void delete(Integer sid) {
		String hql = "delete from Paper where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public Paper getPaper(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(Paper model) {
		this.saveOrUpdateEntity(model);
	}

	public List<Paper> getAllPaper(String author) {
		String hql = "from Paper where author = ?";
		return this.findEntityByHQL(hql, author);
	}

	public List<Paper> getCurrentPaper() {
		int status = 1;
		String hql = "from Paper where status = ?";
		return this.findEntityByHQL(hql, status);
	}

	public boolean isRegisted(String number) {
		String hql = "from Paper where number = ?";
		List<Paper> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list);
	}

	@Override
	public List<Paper> getCurrentPaper(List<String> enumberList) {
		int status = 1;
		String hql = "from Paper where status = ?";
		if (enumberList.size() == 0) {
			return this.findEntityByHQL(hql, status);
		} else {
			//查询试卷号不在results中并且状态为1的所有试卷
			hql = "from Paper where status = "+1+" and number not in(";
			Iterator<String> it = enumberList.iterator();
			while (it.hasNext()) {
				String enumber = it.next();
				if (!it.hasNext()) {
					hql += "'"+enumber+"'";
				} else {
					hql += "'"+enumber+"', ";
				}
			}
			hql += ")";
		}
		System.out.println("hql="+hql);
		return this.findEntityByHQL(hql);
	}

	public Result getAllPaperByPage(Page page) {
		String hql = "from Paper";
		List<Paper> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Paper> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

}
