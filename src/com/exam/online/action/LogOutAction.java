package com.exam.online.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Student;
import com.exam.online.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class LogOutAction extends ActionSupport{

	private static final long serialVersionUID = -7508299496347925925L;
	
	@Resource
	private StudentService studentService;
	
	public String logOut() {
		//获取session会话
		HttpSession s = ServletActionContext.getRequest().getSession();
		Student student = (Student) s.getAttribute("student");//获取当前session中的student对象
		if (student != null || "".equals(student)) {
			student.setStatus(0);//0-离线 1-在线
			studentService.updateEntity(student);//更新对象
		}
		return SUCCESS;
	}
}
