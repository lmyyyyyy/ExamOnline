package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exam.online.dao.BaseDao;
import com.exam.online.domain.Manager;
import com.exam.online.service.ManagerService;
import com.exam.online.util.ValidateUtil;


@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements 
					ManagerService{
	
	@Resource(name="managerDao")
	public void setDao(BaseDao<Manager> dao) {
		super.setDao(dao);
	}

	/**
	 * 查找是否有符合当前账号和密码的用户存在
	 */
	public Manager validateLoginInfo(String number, String password) {
		String hql = "from Manager m where m.number = ? and m.password = ?";
		List<Manager> list = this.findEntityByHQL(hql, number, password);
		return ValidateUtil.isValid(list)?list.get(0):null;//如果list为空的话返回一个null的Manager，如果不为空，返回Manager的list
	}

}
