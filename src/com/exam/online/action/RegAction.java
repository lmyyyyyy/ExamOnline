package com.exam.online.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Student;
import com.exam.online.service.StudentService;
import com.exam.online.util.DataUtil;
import com.exam.online.util.ValidateUtil;




@Controller
@Scope("prototype")
public class RegAction extends BaseAction<Student> {

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
	private StudentService studentService ;
	
	/**
	 * 到达注册页面
	 */
	@SkipValidation
	public String toRegPage(){
		return "regPage" ;
	}
	
	/**
	 * 进行注册
	 */
	public String doReg() {
		model.setNumber(model.getNumber().trim());
		model.setPassword(DataUtil.md5(model.getPassword().trim()));
		model.setName(model.getName().trim());
		model.setSyear(model.getSyear().trim());
		model.setClas(model.getClas().trim());
		model.setMajor(model.getMajor().trim());
		model.setStatus(0);//0代表离线，1代表在线
		studentService.saveEntity(model);
		return SUCCESS ;
	}
	
	@SkipValidation
	public String validateNum() {
		String result = "";//定义一个String类型的结果
		//System.out.println("number1=" + number1);
		if (number1 == null || "".equals(number1)) {
			result = "0";
		} else {
			boolean b = studentService.isRegisted(number1);//如果为true，说明数据库中存在，就不行
			if(b) {
				result="0";
			} else {
				result="1";
			}
		}
		//System.out.println(result);
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
		/*System.out.println("sId="+model.getsId());
		System.out.println("sPassword="+model.getsPassword());
		System.out.println("sName="+model.getsName());
		System.out.println("confirmPassword="+confirmPassword);*/
		//1.非空
		if(!ValidateUtil.isValid(model.getNumber().trim())){
			addFieldError("number", "学号是必填项!");
		}
		if(!ValidateUtil.isValid(model.getPassword().trim())){
			addFieldError("password", "密码是必填项!");
		}
		if(!ValidateUtil.isValid(model.getName().trim())){
			addFieldError("name", "姓名是必填项!");
		}
		if(!ValidateUtil.isValid(model.getClas().trim())){
			addFieldError("clas", "班级是必填项!");
		}
		if(!ValidateUtil.isValid(model.getMajor().trim())){
			addFieldError("major", "专业是必填项!");
		}
		if (model.getNumber().trim().length() > 20) {
			addFieldError("number", "学号不能超过20位");
		}
		if(model.getPassword().trim().length() > 20){
			addFieldError("password", "密码不能超过20位");
		}
		if(model.getName().trim().length() > 10){
			addFieldError("name", "请输入真实姓名");
		}
		if(model.getMajor().trim().length() > 20){
			addFieldError("major", "格式如下例：软件工程");
		}
		if(model.getSyear().trim().toString().length() > 4){
			addFieldError("syear", "格式如下例：2017");
		}
		if(model.getClas().trim().length() > 20){
			addFieldError("clas", "格式如下例：软件二班");
		}
		if(this.hasErrors()){
			return ;
		}
		//2.密码一致性
		if(!model.getPassword().trim().equals(confirmPassword.trim())){
			addFieldError("confirmPassword", "密码输入不一致!");
			return ;
		}
		//3.学号是否占用
		boolean b = studentService.isRegisted(model.getNumber().trim());
		if(b){
			addFieldError("number", "学号已占用!");
		}
	}
}
