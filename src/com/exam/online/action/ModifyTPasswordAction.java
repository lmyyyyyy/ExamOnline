package com.exam.online.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Teacher;
import com.exam.online.service.TeacherService;
import com.exam.online.util.DataUtil;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class ModifyTPasswordAction extends BaseAction<Teacher>{

	private static final long serialVersionUID = 194269844071153603L;

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
	private TeacherService teacherService;
	private Teacher teacher;
	
	public String modifyPass() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		teacher = (Teacher) s.getAttribute("teacher");
		Teacher tea = teacherService.validateLoginInfo(teacher.getNumber(), DataUtil.md5(oldPassword));
		if (tea == null) {
			addFieldError("oldPassword", "原密码错误!");
		} else {
			tea.setPassword(DataUtil.md5(newPassword));
			teacherService.updateEntity(tea);
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
