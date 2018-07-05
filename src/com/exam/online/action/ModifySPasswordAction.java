package com.exam.online.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Student;
import com.exam.online.service.StudentService;
import com.exam.online.util.DataUtil;
import com.exam.online.util.ValidateUtil;


@Controller
@Scope("prototype")
public class ModifySPasswordAction extends BaseAction<Student>{

	private static final long serialVersionUID = 3698932899565676453L;
	
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	@Resource
	private StudentService studentService;
	private Student student;
	
	public String modifyPass() {
		
		HttpSession s = ServletActionContext.getRequest().getSession();//获取当前session中的学生信息
		student = (Student) s.getAttribute("student");
		//验证旧密码是否正确
		Student stu = studentService.validateLoginInfo(student.getNumber(), DataUtil.md5(oldPassword));
		//没取到这个用户就给错误信息
		if (stu == null) {
			addFieldError("oldPassword", "原密码错误!");
		} else {
			//将新密码进行加密保存
			stu.setPassword(DataUtil.md5(newPassword));
			studentService.updateEntity(stu);
			return "toLogin";
		}
		return "toModify";
	}
	
	public void validate() {
		if(!ValidateUtil.isValid(oldPassword)){
			addFieldError("oldPassword", "原密码是必填项!");
		}
		if(!ValidateUtil.isValid(newPassword)){
			addFieldError("newPassword", "新密码是必填项!");
		}
		if(newPassword.length() > 20){
			addFieldError("newPassword", "新密码不能超过20位");
		}

		if(this.hasErrors()){
			return ;
		}
		//密码一致性
		if(!newPassword.equals(confirmPassword)){
			addFieldError("confirmPassword", "密码输入不一致!");
			return ;
		}
	}
}
