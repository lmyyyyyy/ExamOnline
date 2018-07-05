package com.exam.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Judgment;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.JudgmentService;
import com.exam.online.util.ValidateUtil;

@Service("judgmentService")
public class JudgmentServiceImpl extends BaseServiceImpl<Judgment>
				implements JudgmentService{

	@Resource(name="judgmentDao")
	public void setDao(BaseDao<Judgment> dao) {
		super.setDao(dao);
	}

	public List<Judgment> getAllJudgment() {
		String hql = "from Judgment";
		return this.findEntityByHQL(hql);
	}

	public void delete(Integer sid) {
		String hql = "delete from Judgment where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public Judgment getJudgment(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(Judgment model) {
		this.saveOrUpdateEntity(model);
	}

	public int getQuestionCount(String subject, Integer difficult) {
		String hql = "from Judgment where subject = ? and difficult <= ?";
		List<Judgment> list = this.findEntityByHQL(hql, subject, difficult);
		if (list == null) {
			return 0;
		} 
		return list.size();
	}
	
	/**
	 * 抽取判断题
	 */
	public List<Judgment> extract(Integer judgmentCount, String style,
			Integer difficult) {
		List<Judgment> resultList = null;
		List<Judgment> list = null;
		if ("随机组卷".equals(style)) {
			String hql = "from Judgment where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			
			HashSet<Integer> index = getRandom(list.size(), judgmentCount);
			
			int i=0;
			resultList = new ArrayList<Judgment>();
           	
           	for (Iterator<Judgment> iterator= list.iterator();iterator.hasNext(); ) {
				
           		Judgment judgment = iterator.next();
           		if(index.contains(i))
           		{
           			resultList.add(judgment);
           		}
           		i++;
			}
		}
		if ("顺序组卷".equals(style)) {
			String hql = "from Judgment where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			if (list.size() < judgmentCount) {
				return null;
			}
			int index = getRandomOne(list.size() - judgmentCount);
			int i = 0;
			resultList = new ArrayList<Judgment>();
			for (Iterator<Judgment> iterator = list.iterator(); iterator.hasNext();) {
				Judgment judgment = iterator.next();
				if (i < index) {
					i++;
					continue;
				}
				if (i < index + judgmentCount) {
					resultList.add(judgment);
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
	 * 随机获取小于判断数量的索引
	 * @param max
	 * @param judgmentCount
	 * @return
	 */
	public HashSet<Integer> getRandom(int max, int judgmentCount) {
		Random random = new Random();
		HashSet<Integer> s = new HashSet<Integer>();
		while (s.size() < judgmentCount) {
			int i = random.nextInt(max);
			if (!s.contains(i)) {
				s.add(i);
			}
			for(Integer in : s){
		    	   System.out.print("判断题：抽取题号:"+in + " ");
		    }
		}
		return s;
	}

	public Judgment getJudgment(String number) {
		String hql = "from Judgment where number = ?";
		List<Judgment> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}
	
	public boolean isRegisted(String number) {
		String hql = "from Judgment where number = ?";
		List<Judgment> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list);
	}

	/**
	 * 分页获取当前页数的判断题
	 */
	public Result getAllJudgmentByPage(Page page) {
		String hql = "from Judgment";
		List<Judgment> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Judgment> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}
}
