package com.exam.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.SingleChoice;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.SingleChoiceService;
import com.exam.online.util.ValidateUtil;

@Service("singleChoiceService")
public class SingleChoiceServiceImpl extends BaseServiceImpl<SingleChoice>
				implements SingleChoiceService{

	@Resource(name="singleChoiceDao")
	public void setDao(BaseDao<SingleChoice> dao) {
		super.setDao(dao);
	}

	public List<SingleChoice> getAllSingleChoice() {
		String hql = "from SingleChoice";
		return this.findEntityByHQL(hql);
	}
	
	public Result getAllSingleChoiceByPage(Page page) {
		String hql = "from SingleChoice";
		List<SingleChoice> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<SingleChoice> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

	public void delete(Integer sid) {
		String hql = "delete from SingleChoice where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public SingleChoice getSingleChoice(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(SingleChoice model) {
		this.saveOrUpdateEntity(model);
	}

	public int getQuestionCount(String subject, Integer difficult) {
		String hql = "from SingleChoice where subject = ? and difficult <= ?";
		List<SingleChoice> list = this.findEntityByHQL(hql, subject, difficult);
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	/**
	 * 抽取单选题
	 */
	public List<SingleChoice> extract(Integer singleCount, String style,
			Integer difficult) {//单选数量，抽卷方式，难度
		List<SingleChoice> resultList = null;
		List<SingleChoice> list = null;
		if ("随机组卷".equals(style)) {
			String hql = "from SingleChoice where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
//System.out.println("list.size=" + list.size());
			HashSet<Integer> index = getRandom(list.size(), singleCount);//获得不超过库中存在的数量的随机的索引值
			
			int i=0;
			resultList = new ArrayList<SingleChoice>();//用于保存最后的试题集合
           	
           	for (Iterator<SingleChoice> iterator= list.iterator();iterator.hasNext(); ) {
				
           		SingleChoice singleChoice = iterator.next();
           		if(index.contains(i))//如果当前数字在index索引中存在，则添加进集合
           		{
           			resultList.add(singleChoice);
           		}
           		i++;
			}
		}
		if ("顺序组卷".equals(style)) {
			String hql = "from SingleChoice where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			if (list.size() < singleCount) {
				return null;
			}
			int index = getRandomOne(list.size() - singleCount);//先获取一个不大于库中总数量减去需要抽取的题数的随机索引值
			int i = 0;
			resultList = new ArrayList<SingleChoice>();
			for (Iterator<SingleChoice> iterator = list.iterator(); iterator.hasNext();) {
				SingleChoice singleChoice = iterator.next();
				if (i < index) {//如果i小于当前索引，说明还没到需要抽取题的时候，则结束本次循环，进行下次迭代过程
					i++;
					continue;
				}
				if (i < index + singleCount) {//如果i已经不小于索引值了，则在不大于索引值加需要抽取的题的数量的范围内进行迭代添加集合
					resultList.add(singleChoice);
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
	 * 随机获取小于单选数量的索引
	 * @param max
	 * @param singleCount
	 * @return
	 */
	public HashSet<Integer> getRandom(int max, int singleCount) {
		Random random = new Random();
		HashSet<Integer> s = new HashSet<Integer>();
		while (s.size() < singleCount) {
			int i = random.nextInt(max);//获得一个大于0并且不大于max的随机数
			if (!s.contains(i)) {//如果该随机数不在hashset中存在，则添加进去
				s.add(i);
			}
			for(Integer in : s){
		    	   System.out.print("单选题：抽取题号:"+in + " ");
		    }
		}
		return s;
	}

	public SingleChoice getSingleChoice(String number) {
		String hql = "from SingleChoice where number = ?";
		List<SingleChoice> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}
	
	public boolean isRegisted(String number) {
		String hql = "from SingleChoice where number = ?";
		List<SingleChoice> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list);
	}
}
