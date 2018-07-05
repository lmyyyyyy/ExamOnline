package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.ProgramAnswers;
import com.exam.online.service.ProgramAnswersService;

@Service("programAnswersService")
public class ProgramAnswersServiceImpl extends BaseServiceImpl<ProgramAnswers>
				implements ProgramAnswersService{

	@Resource(name="programAnswersDao")
	public void setDao(BaseDao<ProgramAnswers> dao) {
		super.setDao(dao);
	}

	public ProgramAnswers getProgramAnswers(String enumber, String snumber) {
		String hql = "from ProgramAnswers where enumber = ? and snumber = ?";
		List<ProgramAnswers> list = this.findEntityByHQL(hql, enumber, snumber);
		if (list.size() == 0) {
			return null;
		} 
		return list.get(0);
	}

	public List<ProgramAnswers> getAllPAnswers() {
		String hql = "from ProgramAnswers where status = 0 order by regdate desc";
		return this.findEntityByHQL(hql);
	}

	public void changeStatus() {
	}

}
