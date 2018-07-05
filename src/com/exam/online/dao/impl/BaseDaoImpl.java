package com.exam.online.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.exam.online.dao.BaseDao;
import com.exam.online.page.Page;



@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	@Resource
	private SessionFactory sf;
	private Class<T> clazz;
	/**
	 * 得到泛型中的实体类型
	 */
	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	@Resource(name="sessionFactory")  
    public void setSuperSessionFactory(SessionFactory sessionFactory){  
        super.setSessionFactory(sessionFactory);  
    } 
	
	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}
	
	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
	}
	
	public void saveOrUpdateEntity(T t) {
		sf.getCurrentSession().saveOrUpdate(t);
	}
	
	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}
	
	public void batchEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}
	
	public T getEntity(Integer id) {
		return (T) sf.getCurrentSession().get(clazz, id);
	}
	
	public T loadEntity(Integer id) {
		return (T) sf.getCurrentSession().load(clazz, id);
	}
	
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	@SuppressWarnings("rawtypes")
	public List<T> findEntityByHQLPage(final String hql, final Page page, Object... objects) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setMaxResults(page.getEveryPage());
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
			
		});
	}
}
