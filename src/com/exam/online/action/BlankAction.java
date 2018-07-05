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

import com.exam.online.domain.Blank;
import com.exam.online.domain.Subjects;
import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.BlankService;
import com.exam.online.service.SubjectsService;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class BlankAction extends BaseAction<Blank>{

	private static final long serialVersionUID = 5337760059323562744L;

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
	
	private String txNumber;

	public String getTxNumber() {
		return txNumber;
	}

	public void setTxNumber(String txNumber) {
		this.txNumber = txNumber;
	}

	public List<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}
	
	private List<Blank> blankList;

	public List<Blank> getBlankList() {
		return blankList;
	}

	public void setBlankList(List<Blank> blankList) {
		this.blankList = blankList;
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
	private BlankService blankService;
	
	@SkipValidation
	public String toAddBlank() {
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyymmddHHMMss");
	    String date=sdf.format(new Date());
		this.txNumber = "TX"+date;
		this.subjectsList = subjectsService.getAllSubjects();
		return "toAddBlank";
	}
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String allBlank() {
		//this.blankList = blankService.getAllBlank();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = blankService.getAllBlankByPage(page);
		page = result.getPage();
		this.blankList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "findAllBlank";
	}
	
	@SkipValidation
	public String delete() {
		blankService.delete(sid);
		return "deleteBlank";
	}
	
	@SkipValidation
	public String modify() {
		this.model = blankService.getBlank(sid);
		return "modifyBlank";
	}
	
	@SkipValidation
	public String view() {
		this.model = blankService.getBlank(sid);
		return "viewBlank";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdate(){
		blankService.saveOrUpdate(model);
		inputPage = "/teacher/modifyBlank.jsp";
		return "tofindAllBlank" ;
	}
	
	public String doAdd() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Teacher teacher = (Teacher) s.getAttribute("teacher");
		model.setAuthor(teacher.getName());
		blankService.saveEntity(model);
		return SUCCESS;
	}
	
	public void validate() {
		/*System.out.println("题号="+model.getNumber());
		System.out.println("问题="+model.getQuestion());
		System.out.println("答案="+model.getAnswer());
		System.out.println("科目="+model.getSubject());
		System.out.println("难度="+model.getDifficult());*/
		inputPage = "/teacher/addBlank.jsp";
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
		boolean b = blankService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "题号已占用!");
		}
	}
}
