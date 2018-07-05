package com.exam.online.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Subjects;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.SubjectsService;

/**
 * 处理科目
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class SubjectsAction extends BaseAction<Subjects>{

	private static final long serialVersionUID = -2062374231916239835L;

	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
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
	private SubjectsService subjectsService;
	
	
	public String doAddSubjects() {
		System.out.println(model.getName());
		if (model.getName() == null || "".equals(model.getName()) || model.getName().length() > 20){
			addFieldError("name", "不支持的名称");
			inputPage = "/teacher/addSubjects.jsp";
			return INPUT;
		}
		subjectsService.saveEntity(model);
		return SUCCESS;
	}
	
	private List<Subjects> subjectsList;
	
	public List<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}

	@SuppressWarnings("unchecked")
	public String allSubjects() {
		//this.subjectsList = subjectsService.getAllSubjects();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = subjectsService.getAllSubjectsByPage(page);
		page = result.getPage();
		this.subjectsList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "findAllSubjects";
	}
	
	@SkipValidation
	public String delete() {
		subjectsService.delete(sid);
		return "deleteSubjects";
	}
	
	@SkipValidation
	public String modify() {
		this.model = subjectsService.getSubjects(sid);
		return "modifySubjects";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdate(){
		subjectsService.saveOrUpdate(model);
		inputPage = "/teacher/modifySubjects.jsp";
		return "tofindAllSubjects" ;
	}
}
