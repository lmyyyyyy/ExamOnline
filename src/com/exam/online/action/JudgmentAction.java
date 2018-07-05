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

import com.exam.online.domain.Judgment;
import com.exam.online.domain.Subjects;
import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.JudgmentService;
import com.exam.online.service.SubjectsService;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class JudgmentAction extends BaseAction<Judgment>{

	private static final long serialVersionUID = 2654097211142222828L;

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
	
	private String pdNumber;

	public String getPdNumber() {
		return pdNumber;
	}

	public void setPdNumber(String pdNumber) {
		this.pdNumber = pdNumber;
	}

	public List<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}
	
	private List<Judgment> judgmentList;

	public List<Judgment> getJudgmentList() {
		return judgmentList;
	}

	public void setJudgmentList(List<Judgment> judgmentList) {
		this.judgmentList = judgmentList;
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
	private JudgmentService judgmentService;
	
	@SkipValidation
	public String toAddJudgment() {
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyymmddHHMMss");
	    String date=sdf.format(new Date());
		this.pdNumber = "PD"+date;
		this.subjectsList = subjectsService.getAllSubjects();
		return "toAddJudgment";
	}
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String allJudgment() {
		//this.judgmentList = judgmentService.getAllJudgment();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = judgmentService.getAllJudgmentByPage(page);
		page = result.getPage();
		this.judgmentList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "findAllJudgment";
	}
	
	@SkipValidation
	public String delete() {
		judgmentService.delete(sid);
		return "deleteJudgment";
	}
	
	@SkipValidation
	public String modify() {
		this.model = judgmentService.getJudgment(sid);
		return "modifyJudgment";
	}
	
	@SkipValidation
	public String view() {
		this.model = judgmentService.getJudgment(sid);
		return "viewJudgment";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdate(){
		judgmentService.saveOrUpdate(model);
		inputPage = "/teacher/modifyJudgment.jsp";
		return "tofindAllJudgment" ;
	}
	
	public String doAdd() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Teacher teacher = (Teacher) s.getAttribute("teacher");
		model.setAuthor(teacher.getName());
		judgmentService.saveEntity(model);
		return SUCCESS;
	}
	
	public void validate() {
		/*System.out.println("题号="+model.getNumber());
		System.out.println("问题="+model.getQuestion());
		System.out.println("科目="+model.getSubject());
		System.out.println("难度="+model.getDifficult());*/
		inputPage = "/teacher/addJudgment.jsp";
		if(!ValidateUtil.isValid(model.getQuestion())){
			addFieldError("question", "问题是必填项!");
		}
		if(!ValidateUtil.isValid(model.getAnswer())){
			addFieldError("answer", "答案是必填项!");
		}
		if(this.hasErrors()){
			return ;
		}
		
		//3.number是否占用
		boolean b = judgmentService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "题号已占用!");
		}
	}
}
