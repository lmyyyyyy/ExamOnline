package com.exam.online.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.online.dao.BaseDao;
import com.exam.online.page.Page;
import com.exam.online.service.BaseService;


public abstract class BaseServiceImpl<T> extends HibernateDaoSupport implements BaseService<T> {
	
	private BaseDao<T> dao;
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}
	
	@Resource(name="sessionFactory")  
    public void setSuperSessionFactory(SessionFactory sessionFactory){  
        super.setSessionFactory(sessionFactory);  
    } 
	
	public void saveEntity(T t) {
		dao.saveEntity(t);
	}
	
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}
	
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}
	
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}
	
	
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}
	
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}
	
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}
	
	public List<T> findEntityByHQLPage(String hql, Page page, Object... objects) {
		return dao.findEntityByHQLPage(hql, page, objects);
	}
}
