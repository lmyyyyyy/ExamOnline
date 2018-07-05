package com.exam.online.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Program;
import com.exam.online.domain.Subjects;
import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.ProgramService;
import com.exam.online.service.SubjectsService;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class ProgramAction extends BaseAction<Program>{

	private static final long serialVersionUID = -6163606497190053371L;

	private List<Subjects> subjectsList;
	
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
	
	private String bcNumber;

	public String getBcNumber() {
		return bcNumber;
	}

	public void setBcNumber(String bcNumber) {
		this.bcNumber = bcNumber;
	}

	
	public List<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}
	
	private List<Program> programList;

	public List<Program> getProgramList() {
		return programList;
	}

	public void setProgramList(List<Program> programList) {
		this.programList = programList;
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
	@Resource
	private ProgramService programService;
	
	@SkipValidation
	public String toAddProgram() {
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyymmddHHMMss");
	    String date=sdf.format(new Date());
		this.bcNumber = "BC"+date;
		this.subjectsList = subjectsService.getAllSubjects();
		return "toAddProgram";
	}
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String allProgram() {
		//this.programList = programService.getAllProgram();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = programService.getAllProgramByPage(page);
		page = result.getPage();
		this.programList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "findAllProgram";
	}
	
	@SkipValidation
	public String delete() {
		programService.delete(sid);
		return "deleteProgram";
	}
	
	@SkipValidation
	public String modify() {
		this.model = programService.getProgram(sid);
		return "modifyProgram";
	}
	
	@SkipValidation
	public String view() {
		this.model = programService.getProgram(sid);
		return "viewProgram";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdate(){
		programService.saveOrUpdate(model);
		inputPage = "/teacher/modifyProgram.jsp";
		return "tofindAllProgram" ;
	}
	
	public String doAdd() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Teacher teacher = (Teacher) s.getAttribute("teacher");
		model.setAuthor(teacher.getName());
		programService.saveEntity(model);
		return SUCCESS;
	}
	
	public void validate() {
		/*System.out.println("题号="+model.getNumber());
		System.out.println("问题="+model.getQuestion());
		System.out.println("选项A="+model.getChoicea());
		System.out.println("选项B="+model.getChoiceb());
		System.out.println("选项C="+model.getChoicec());
		System.out.println("选项D="+model.getChoiced());
		System.out.println("答案="+model.getAnswer());
		System.out.println("科目="+model.getSubject());
		System.out.println("难度="+model.getDifficult());*/
		inputPage = "/teacher/addProgram.jsp";
		if(!ValidateUtil.isValid(model.getQuestion())){
			addFieldError("question", "问题是必填项!");
		}
		if(this.hasErrors()){
			return ;
		}
		
		//3.number是否占用
		boolean b = programService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "题号已占用!");
		}
	}
}
