package com.exam.online.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ChekLoginInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		//获取ActionContext
		ActionContext ac = invocation.getInvocationContext();
		//过去session;
		Map<String, Object> session = ac.getSession();
		//获取session中的用户信息
		Object student = session.get("student");
		Object teacher = session.get("teacher");
		Object manager = session.get("manager");
		if (student != null || teacher != null || manager != null) {
			System.out.println("当前有session");
			return invocation.invoke();
		} else {
			System.out.println("当前无session");
			return "login";
		}
	}

}
