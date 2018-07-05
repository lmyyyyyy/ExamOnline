package com.exam.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Program;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.ProgramService;
import com.exam.online.util.ValidateUtil;

@Service("programService")
public class ProgramServiceImpl extends BaseServiceImpl<Program>
				implements ProgramService{

	@Resource(name="programDao")
	public void setDao(BaseDao<Program> dao) {
		super.setDao(dao);
	}

	public List<Program> getAllProgram() {
		String hql = "from Program";
		return this.findEntityByHQL(hql);
	}

	public void delete(Integer sid) {
		String hql = "delete from Program where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public Program getProgram(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(Program model) {
		this.saveOrUpdateEntity(model);
	}

	public int getQuestionCount(String subject, Integer difficult) {
		String hql = "from Program where subject = ? and difficult <= ?";
		List<Program> list = this.findEntityByHQL(hql, subject, difficult);
		if (list == null) {
			return 0;
		}
		return list.size();
	}
	
	/**
	 * 抽取编程题
	 */
	public List<Program> extract(Integer programCount, String style,
			Integer difficult) {
		List<Program> resultList = null;
		List<Program> list = null;
		if ("随机组卷".equals(style)) {
			String hql = "from Program where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			
			HashSet<Integer> index = getRandom(list.size(),programCount);
			
			int i=0;
			resultList = new ArrayList<Program>();
           	
           	for (Iterator<Program> iterator= list.iterator();iterator.hasNext(); ) {
				
           		Program program = iterator.next();
           		if(index.contains(i))
           		{
           			resultList.add(program);
           		}
           		i++;
			}
		}
		if ("顺序组卷".equals(style)) {
			String hql = "from Program where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			if (list.size() < programCount) {
				return null;
			}
			int index = getRandomOne(list.size() - programCount);
			int i = 0;
			resultList = new ArrayList<Program>();
			for (Iterator<Program> iterator = list.iterator(); iterator.hasNext();) {
				Program program = iterator.next();
				if (i < index) {
					i++;
					continue;
				}
				if (i < index + programCount) {
					resultList.add(program);
				}
				i++;
			}
		}
		return resultList;
	}
	
	/**
	 * 根据总题数减去抽取数算出的数，获取小于该数的随机数
	 * @param i
	 * @return
	 */
	private int getRandomOne(int i) {
		if (i<=0) {
			return 0;
		}
		Random random = new Random();
		int index = random.nextInt(i);
		return index;
	}
	/**
	 * 随机获取小于编程数量的索引
	 * @param max
	 * @param programCount
	 * @return
	 */
	public HashSet<Integer> getRandom(int max, int programCount) {
		Random random = new Random();
		HashSet<Integer> s = new HashSet<Integer>();
		while (s.size() < programCount) {
			int i = random.nextInt(max);
			if (!s.contains(i)) {
				s.add(i);
			}
			for(Integer in : s){
		    	   System.out.print("编程题：抽取题号:"+in + " ");
		    }
		}
		return s;
	}

	public Program getProgram(String number) {
		String hql = "from Program where number = ?";
		List<Program> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}
	
	public boolean isRegisted(String number) {
		String hql = "from Program where number = ?";
		List<Program> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list);
	}

	@Override
	public Result getAllProgramByPage(Page page) {
		String hql = "from Program";
		List<Program> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Program> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}
}
