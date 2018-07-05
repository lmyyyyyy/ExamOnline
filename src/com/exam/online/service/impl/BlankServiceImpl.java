package com.exam.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Blank;
import com.exam.online.page.Page;
import com.exam.online.page.PageUtil;
import com.exam.online.page.Result;
import com.exam.online.service.BlankService;
import com.exam.online.util.ValidateUtil;

@Service("blankService")
public class BlankServiceImpl extends BaseServiceImpl<Blank>
				implements BlankService{

	@Resource(name="blankDao")
	public void setDao(BaseDao<Blank> dao) {
		super.setDao(dao);
	}

	public List<Blank> getAllBlank() {
		String hql = "from Blank";
		return this.findEntityByHQL(hql);
	}

	public void delete(Integer sid) {
		String hql = "delete from Blank where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public Blank getBlank(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(Blank model) {
		this.saveOrUpdateEntity(model);
	}

	public int getQuestionCount(String subject, Integer difficult) {
		String hql = "from Blank where subject = ? and difficult <= ?";
		List<Blank> list = this.findEntityByHQL(hql, subject, difficult);
		if (list == null) {
			return 0;
		}
		return list.size();
	}
	
	/**
	 * 抽取填空题
	 */
	public List<Blank> extract(Integer blankCount, String style,
			Integer difficult) {
		List<Blank> resultList = null;
		List<Blank> list = null;
		if ("随机组卷".equals(style)) {
			String hql = "from Blank where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			
			HashSet<Integer> index = getRandom(list.size(), blankCount);
			
			int i=0;
			resultList = new ArrayList<Blank>();
           	
           	for (Iterator<Blank> iterator= list.iterator();iterator.hasNext(); ) {
				
           		Blank blank = iterator.next();
           		if(index.contains(i))
           		{
           			resultList.add(blank);
           		}
           		i++;
			}
		}
		if ("顺序组卷".equals(style)) {
			String hql = "from Blank where difficult <= ?";
			list = this.findEntityByHQL(hql, difficult);
			if (list.size() < blankCount) {
				return null;
			}
			int index = getRandomOne(list.size() - blankCount);
			int i = 0;
			resultList = new ArrayList<Blank>();
			for (Iterator<Blank> iterator = list.iterator(); iterator.hasNext();) {
				Blank blank = iterator.next();
				if (i < index) {
					i++;
					continue;
				}
				if (i < index + blankCount) {
					resultList.add(blank);
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
	 * 随机获取小于填空数量的索引
	 * @param max
	 * @param blankCount
	 * @return
	 */
	public HashSet<Integer> getRandom(int max, int blankCount) {
		Random random = new Random();
		HashSet<Integer> s = new HashSet<Integer>();
		while (s.size() < blankCount) {
			int i = random.nextInt(max);
			if (!s.contains(i)) {
				s.add(i);
			}
			for(Integer in : s){
		    	   System.out.print("填空题：抽取题号:"+in + " ");
		    }
		}
		return s;
	}

	public Blank getBlank(String number) {
		String hql = "from Blank where number = ?";
		List<Blank> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}
	
	public boolean isRegisted(String number) {
		String hql = "from Blank where number = ?";
		List<Blank> list = this.findEntityByHQL(hql, number);
		return ValidateUtil.isValid(list);
	}

	/**
	 * 分页获取当前页数的填空题
	 */
	public Result getAllBlankByPage(Page page) {
		String hql = "from Blank";
		List<Blank> list = this.findEntityByHQL(hql);
		page = PageUtil.createPage(page, list.size());
		List<Blank> all = this.findEntityByHQLPage(hql, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

}
