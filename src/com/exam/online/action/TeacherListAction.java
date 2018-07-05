package com.exam.online.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.TeacherService;
import com.exam.online.util.DataUtil;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class TeacherListAction extends BaseAction<Teacher>{

	private static final long serialVersionUID = 3545368643142801360L;
	
	private List<Teacher> teacherList;
	
	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	
	private Integer tid;
	
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	private String inputPage;

	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}
	
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Resource
	private TeacherService teacherService;
	
	public String doAdd() {
		model.setPassword(DataUtil.md5(model.getPassword()));
		teacherService.saveEntity(model);
		return SUCCESS ;
	}
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String findTeacherList() {
		//this.teacherList = teacherService.getAllTeacher();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = teacherService.getAllTeacherByPage(page);
		page = result.getPage();
		System.out.println("总页数为"+page.getTotalPage());
		this.teacherList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "toTeacherListPage";
	}
	
	@SkipValidation
	public String deleteTea() {
		teacherService.deleteTeacher(tid);
		return "findAllTeaAction";
	}
	
	@SkipValidation
	public String modifyTea() {
		this.model = teacherService.getTeacher(tid);
		return "modifyTeacher";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdateTea(){
		teacherService.saveOrUpdateTea(model);
		inputPage = "/manager/modifyTea.jsp";
		return "findAllTeaAction" ;
	}
	
	public void validate() {
		inputPage = "/manager/addTeacher.jsp";
		/*System.out.println("sId="+model.getNumber());
		System.out.println("sPassword="+model.getPassword());
		System.out.println("sName="+model.getName());*/
		//1.非空
		if(!ValidateUtil.isValid(model.getNumber())){
			addFieldError("number", "编号是必填项!");
		}
		if(!ValidateUtil.isValid(model.getPassword())){
			addFieldError("password", "密码是必填项!");
		}
		if(!ValidateUtil.isValid(model.getName())){
			addFieldError("name", "姓名是必填项!");
		}

		if (model.getNumber().length() > 20) {
			addFieldError("number", "编号不能超过20位");
		}
		if(model.getPassword().length() > 20){
			addFieldError("password", "密码不能超过20位");
		}
		if(model.getName().length() > 10){
			addFieldError("name", "请输入真实姓名");
		}

		if(this.hasErrors()){
			return ;
		}
		
		//3.email是否占用
		boolean b = teacherService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "编号已占用!");
		}
		
	}
}
