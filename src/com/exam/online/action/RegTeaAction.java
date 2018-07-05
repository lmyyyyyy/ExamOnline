package com.exam.online.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Teacher;
import com.exam.online.service.TeacherService;
import com.exam.online.util.DataUtil;
import com.exam.online.util.ValidateUtil;



@Controller
@Scope("prototype")
public class RegTeaAction extends BaseAction<Teacher> {

	private static final long serialVersionUID = -6134513189257569056L;
	private String number1;
	public String getNumber1() {
		return number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	//确认密码
	private String confirmPassword ;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	//注入userService
	@Resource
	private TeacherService teacherService ;
	
	/**
	 * 到达注册页面
	 */
	@SkipValidation
	public String toRegPage(){
		return "regTeaPage" ;
	}
	
	/**
	 * 进行注册
	 */
	public String doReg() {
		model.setNumber(model.getNumber().trim());
		model.setPassword(DataUtil.md5(model.getPassword()));
		model.setName(model.getName().trim());
		teacherService.saveEntity(model);
		return SUCCESS ;
	}
	
	@SkipValidation
	public String validateNum() {
		String result = "";
		System.out.println("number1=" + number1);
		if (number1 == null || "".equals(number1)) {
			result = "0";
		} else {
			boolean b = teacherService.isRegisted(number1);
			if(b) {
				result="0";
			} else {
				result="1";
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer;

		try {
			writer = response.getWriter();

			 writer.print(result); 
			 writer.flush();  
			 writer.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void validate() {
		/*System.out.println("sId="+model.getNumber());
		System.out.println("sPassword="+model.getPassword());
		System.out.println("sName="+model.getName());
		System.out.println("confirmPassword="+confirmPassword);*/
		//1.非空
		if(!ValidateUtil.isValid(model.getNumber().trim())){
			addFieldError("number", "编号是必填项!");
		}
		if(!ValidateUtil.isValid(model.getPassword().trim())){
			addFieldError("password", "密码是必填项!");
		}
		if(!ValidateUtil.isValid(model.getName().trim())){
			addFieldError("name", "姓名是必填项!");
		}

		if (model.getNumber().trim().length() > 20) {
			addFieldError("number", "编号不能超过20位");
		}
		if(model.getPassword().trim().length() > 20){
			addFieldError("password", "密码不能超过20位");
		}
		if(model.getName().trim().length() > 10){
			addFieldError("name", "请输入真实姓名");
		}

		if(this.hasErrors()){
			return ;
		}
		//2.密码一致性
		if(!model.getPassword().trim().equals(confirmPassword.trim())){
			addFieldError("confirmPassword", "密码输入不一致!");
			return ;
		}
		//3.number是否占用
		boolean b = teacherService.isRegisted(model.getNumber().trim());
		if(b){
			addFieldError("number", "编号已占用!");
		}
		
	}

	
}
