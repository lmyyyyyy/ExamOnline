package com.exam.online.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 抽象的action超类,专门用于继承
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,
		Preparable {

	private static final long serialVersionUID = -1314560516485837572L;
	
	public T model ;
	
	@SuppressWarnings("unchecked")
	public BaseAction(){
		try {
			//获取java泛型参数类型
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			this.model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void prepare() throws Exception {
	}

	public T getModel(){
		return this.model ;
	}
}
