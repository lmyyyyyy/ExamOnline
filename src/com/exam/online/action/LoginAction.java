package com.exam.online.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Manager;
import com.exam.online.domain.Student;
import com.exam.online.domain.Teacher;
import com.exam.online.service.ManagerService;
import com.exam.online.service.StudentService;
import com.exam.online.service.TeacherService;
import com.exam.online.util.DataUtil;
import com.exam.online.util.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 2137870347147592561L;
	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private ManagerService managerService;
	/**
	 * 接受session的map
	 */
	private Map<String, Object> sessionMap;
	
	private String number;
	private String password;
	private String role;
	
	/**
	 *进入登录页面 
	 */
	public String toLoginPage() {
		return "loginPage";
	}
	
	public String doLogin() {
		
		//验证前台传过来的账号和密码是否为空
		//如果其中一项为空，那么给错误信息，返回到原页面
		if (!ValidateUtil.isValid(number) || !ValidateUtil.isValid(password)) {
			addActionError("编号或密码不能为空!");
			return "error";
		}
		//判断当前选择的角色是什么
		//如果为管理员
		if (role.equals("manager")) {
			//验证当前的账号和加密后的密码在数据库里是否存在
			Manager manager = managerService.validateLoginInfo(number, DataUtil.md5(password));
			//如果manager为空的话
			if (manager == null) {
				//给错误信息
				addActionError("编号或密码错误");
			} else {
				//否则将当前的manager实体存在session中
				sessionMap.put("manager", manager);
				//跳转到管理员页面
				return "toManager";
			}
		}
		if (role.equals("student")) {
			Student student = studentService.validateLoginInfo(number, DataUtil.md5(password));
			if (student == null) {
				addActionError("编号或密码错误");
			} else {
				//如果存在，在登录的时候，改变状态为1
				student.setStatus(1);//在线
				studentService.updateEntity(student);
				sessionMap.put("student",student);
				return "toStudent";
			}
		} else if (role.equals("teacher")) {
			Teacher teacher = teacherService.validateLoginInfo(number, DataUtil.md5(password));
			if (teacher == null) {
				addActionError("编号或密码错误");
			} else {
				sessionMap.put("teacher", teacher);
				return "toTeacher";
			}
		}
		return "error";
		
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
}
