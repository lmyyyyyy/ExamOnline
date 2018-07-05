package com.exam.online.service;

import com.exam.online.domain.Manager;

public interface ManagerService extends BaseService<Manager>{

	/**
	 * 校验登录信息
	 */
	public Manager validateLoginInfo(String sId, String md5);
}
