package com.exam.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.MultipleChoice;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.MultipleChoiceService;
import com.exam.online.util.ValidateUtil;

@Service("multipleChoiceService")
public class MultipleChoiceServiceImpl extends BaseServiceImpl<MultipleChoice>
				implements MultipleChoiceService{

	@Resource(name="multipleChoiceDao")
	public void setDao(BaseDao<MultipleChoice> dao) {
		super.setDao(dao);
	}

	public List<MultipleChoice> getAllMultipleChoice() {
		String hql = "from MultipleChoice";
		return this.findEntityByHQL(hql);
	}

	public void delete(Integer sid) {
		String hql = "delete from MultipleChoice where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public MultipleChoice getSingleChoice(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(MultipleChoice model) {
		this.saveOrUpdateEntity(model);
	}

	public int getQuestionCount(String subject, Integer difficult) {
		String hql = "from MultipleChoice where subject = ? and difficult <= ?";
		List<MultipleChoice> list = this.findEntityByHQL(hql, subject, difficult);
		if (list == null) {
			return 0;
		}
		return list.size();
	}
	
	/**
	 * 抽取多选题
	 */
	public List<MultipleChoice> extract(Integer multipleChoiceCount, String style,
			Integer difficult) {
		List<MultipleChoice> resultList = null;
		List<MultipleChoice> list = null;
		if ("随机组卷".equals(style)) {
			String hql = "from MultipleChoice where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			
			HashSet<Integer> index = getRandom(list.size(), multipleChoiceCount);
			
			int i=0;
			resultList = new ArrayList<MultipleChoice>();
           	
           	for (Iterator<MultipleChoice> iterator= list.iterator();iterator.hasNext(); ) {
				
           		MultipleChoice multipleChoice = iterator.next();
           		if(index.contains(i))
           		{
           			resultList.add(multipleChoice);
           		}
           		i++;
			}
		}
		if ("顺序组卷".equals(style)) {
			String hql = "from MultipleChoice where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			if (list.size() < multipleChoiceCount) {
				return null;
			}
			int index = getRandomOne(list.size() - multipleChoiceCount);
			int i = 0;
			resultList = new ArrayList<MultipleChoice>();
			for (Iterator<MultipleChoice> iterator = list.iterator(); iterator.hasNext();) {
				MultipleChoice multipleChoice = iterator.next();
				if (i < index) {
					i++;
					continue;
				}
				if (i < index + multipleChoiceCount) {
					resultList.add(multipleChoice);
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
	 * 随机获取小于多选数量的索引
	 * @param max
	 * @param multipleCount
	 * @return
	 */
	public HashSet<Integer> getRandom(int max, int multipleCount) {
		Random random = new Random();
		HashSet<Integer> s = new HashSet<Integer>();
		while (s.size() < multipleCount) {
			int i = random.nextInt(max);
			if (!s.contains(i)) {
				s.add(i);
			}
			for(Integer in : s){
		    	   System.out.print("多选题：抽取题号:"+in + " ");
		    }
		}
		return s;
	}

	public MultipleChoice getMultipleChoice(String number) {
		String hql = "from MultipleChoice where number = ?";
		List<MultipleChoice> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}
	
	public boolean isRegisted(String number) {
		String hql = "from MultipleChoice where number = ?";
		List<MultipleChoice> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list);
	}

	/**
	 * 分页获取当前页数的多选题
	 */
	public Result getAllMultipleChoiceByPage(Page page) {
		String hql = "from MultipleChoice";
		List<MultipleChoice> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<MultipleChoice> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}
}
