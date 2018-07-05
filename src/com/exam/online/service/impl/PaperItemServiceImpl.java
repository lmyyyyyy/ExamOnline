package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.PaperItem;
import com.exam.online.service.PaperItemService;

@Service("paperItemService")
public class PaperItemServiceImpl extends BaseServiceImpl<PaperItem>
				implements PaperItemService{

	@Resource(name="paperItemDao")
	public void setDao(BaseDao<PaperItem> dao) {
		super.setDao(dao);
	}

	public List<PaperItem> getAllPaperItem() {
		String hql = "from PaperItem";
		return this.findEntityByHQL(hql);
	}

	public void delete(Integer sid) {
		String hql = "delete from PaperItem where id = ?";
		this.batchEntityByHQL(hql, sid);
	}

	public PaperItem getPaperItem(Integer sid) {
		return this.getEntity(sid);
	}

	public void saveOrUpdate(PaperItem model) {
		this.saveOrUpdateEntity(model);
	}

	public List<PaperItem> getAllPaperItem(String author) {
		String hql = "from PaperItem where author = ?";
		return this.findEntityByHQL(hql, author);
	}

	public List<PaperItem> getPaperItem(String paperNumber) {
		String hql = "from PaperItem where enumber = ?";
		return this.findEntityByHQL(hql, paperNumber);
	}

	public List<PaperItem> getSingleChoice(String paperNumber, String type) {
		String hql = "from PaperItem where enumber = ? and type = ?";
		return this.findEntityByHQL(hql, paperNumber,type);
	}

	public List<PaperItem> getMultipleChoice(String paperNumber,
			String type) {
		String hql = "from PaperItem where enumber = ? and type = ?";
		return this.findEntityByHQL(hql, paperNumber,type);
	}

	public List<PaperItem> getBlank(String paperNumber, String type) {
		String hql = "from PaperItem where enumber = ? and type = ?";
		return this.findEntityByHQL(hql, paperNumber,type);
	}

	public List<PaperItem> getJudgment(String paperNumber, String type) {
		String hql = "from PaperItem where enumber = ? and type = ?";
		return this.findEntityByHQL(hql, paperNumber,type);
	}

	public List<PaperItem> getProgram(String paperNumber, String type) {
		String hql = "from PaperItem where enumber = ? and type = ?";
		return this.findEntityByHQL(hql, paperNumber,type);
	}

}
