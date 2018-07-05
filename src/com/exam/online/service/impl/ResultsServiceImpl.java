package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Results;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.ResultsService;

@Service("resultsService")
public class ResultsServiceImpl extends BaseServiceImpl<Results>
				implements ResultsService{

	@Resource(name="resultsDao")
	public void setDao(BaseDao<Results> dao) {
		super.setDao(dao);
	}

	/**
	 * 学生查询自己的成绩
	 */
	public List<Results> getResults(String snumber) {
		String hql = "from Results where snumber = ?  and totalscore IS NOT null order by regdate desc";
		return this.findEntityByHQL(hql, snumber);
	}
	
	public Result getResultsByPage(Page page,String snumber) {
		String hql = "from Results where snumber = '"+snumber+"'  and totalscore IS NOT null order by regdate desc";
		List<Results> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Results> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

	public Results isRegisted(String enumber, String snumber) {
		String hql = "from Results where enumber = ? and snumber = ?";
		List<Results> list = this.findEntityByHQL(hql, enumber, snumber);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 教师查询学生成绩
	 */
	public List<Results> getAllResults() {
		String hql = "from Results where totalscore IS NOT null order by regdate desc";
		return this.findEntityByHQL(hql);
	}

	public List<Results> getAllResults(String type, String query) {
		String hql = "from Results order by regdate desc";
		if (query == null || "".equals(query)) {
			return this.findEntityByHQL(hql);
		} else {
			if (type.equals("subjcet")) {
				hql = "from Results s where s.subjcet like '%"+query+"%' order by regdate desc";
			} else if (type.equals("snumber")){
				hql = "from Results s where s.snumber like '%"+query+"%' order by regdate desc";
			} else if (type.equals("sname")) {
				hql = "from Results s where s.sname like '%"+query+"%' order by regdate desc";
			} else if (type.equals("ename")) {
				hql = "from Results s where s.ename like '%"+query+"%' order by regdate desc";
			} else if (type.equals("clas")) {
				hql = "from Results s where s.sclas like '%"+query+"%' order by regdate desc";
			} 
		}
		return this.findEntityByHQL(hql);
	}

	/**
	 * 为了答完某个试卷在考试列表中除去该试卷
	 */
	public List<Results> getAllResults(String number) {
		String hql = "from Results where snumber = ? order by regdate desc";
		return this.findEntityByHQL(hql, number);
	}

	public Result getAllResultsByPage(Page page, String type, String query) {
		String hql = "from Results order by regdate desc";
		
		if (query == null || "".equals(query)) {
			hql = "from Results order by regdate desc";
		} else {
			if (type.equals("subjcet")) {
				hql = "from Results s where s.subjcet like '%"+query+"%' order by regdate desc";
			} else if (type.equals("snumber")){
				hql = "from Results s where s.snumber like '%"+query+"%' order by regdate desc";
			} else if (type.equals("sname")) {
				hql = "from Results s where s.sname like '%"+query+"%' order by regdate desc";
			} else if (type.equals("ename")) {
				hql = "from Results s where s.ename like '%"+query+"%' order by regdate desc";
			} else if (type.equals("clas")) {
				hql = "from Results s where s.sclas like '%"+query+"%' order by regdate desc";
			} 
		}
		List<Results> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Results> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

	public Result getAllResultsByPage(Page page) {
		String hql = "from Results";
		List<Results> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Results> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

	public List<Results> getCurrentResults(String enumber) {
		String hql = "from Results where enumber = ? order by totalscore desc";
		return this.findEntityByHQL(hql, enumber);
	}

}
