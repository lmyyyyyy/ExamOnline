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

import com.exam.online.domain.MultipleChoice;
import com.exam.online.domain.Subjects;
import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.MultipleChoiceService;
import com.exam.online.service.SubjectsService;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class MultipleChoiceAction extends BaseAction<MultipleChoice>{

	private static final long serialVersionUID = 8060856173599536081L;
	
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
	
	private String MxNumber;
	
	public String getMxNumber() {
		return MxNumber;
	}

	public void setMxNumber(String mxNumber) {
		MxNumber = mxNumber;
	}

	private List<Subjects> subjectsList;
	
	public List<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}
	
	private List<MultipleChoice> multipleChoiceList;

	public List<MultipleChoice> getMultipleChoiceList() {
		return multipleChoiceList;
	}

	public void setMultipleChoiceList(List<MultipleChoice> multipleChoiceList) {
		this.multipleChoiceList = multipleChoiceList;
	}
	
	@Resource
	private SubjectsService subjectsService;
	@Resource
	private MultipleChoiceService multipleChoiceService;
	
	@SkipValidation
	public String toAddMultiple() {
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyymmddHHMMss");
	    String date=sdf.format(new Date());
		this.MxNumber = "MX"+date;
		this.subjectsList = subjectsService.getAllSubjects();
		return "toAddMultiple";
	}
	
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String allMultipleChoice() {
		//this.multipleChoiceList = multipleChoiceService.getAllMultipleChoice();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = multipleChoiceService.getAllMultipleChoiceByPage(page);
		page = result.getPage();
		this.multipleChoiceList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "findAllMultipleChoice";
	}
	
	@SkipValidation
	public String delete() {
		multipleChoiceService.delete(sid);
		return "deleteMultipleChoice";
	}
	
	@SkipValidation
	public String modify() {
		this.model = multipleChoiceService.getSingleChoice(sid);
		return "modifyMultipleChoice";
	}
	
	@SkipValidation
	public String view() {
		this.model = multipleChoiceService.getSingleChoice(sid);
		return "viewMultipleChoice";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdate(){
		multipleChoiceService.saveOrUpdate(model);
		inputPage = "/teacher/modifySingleChoice.jsp";
		return "tofindAllMultipleChoice" ;
	}
	
	public String doAdd() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Teacher teacher = (Teacher) s.getAttribute("teacher");
		model.setAuthor(teacher.getName());
		multipleChoiceService.saveEntity(model);
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
		inputPage = "/teacher/addMultipleChoice.jsp";
		System.out.println("科目="+model.getSubject());
		if(!ValidateUtil.isValid(model.getQuestion())){
			addFieldError("question", "问题是必填项!");
		}
		if(!ValidateUtil.isValid(model.getChoicea())){
			addFieldError("choicea", "选项A是必填项!");
		}
		if(!ValidateUtil.isValid(model.getChoiceb())){
			addFieldError("choiceb", "选项B是必填项!");
		}
		if(!ValidateUtil.isValid(model.getChoicec())){
			addFieldError("choicec", "选项C是必填项!");
		}
		if(!ValidateUtil.isValid(model.getChoiced())){
			addFieldError("choiced", "选项D是必填项!");
		}
		if(!ValidateUtil.isValid(model.getAnswer())){
			addFieldError("answer", "答案是必填项!");
		}
		
		if(this.hasErrors()){
			return ;
		}
		
		//3.number是否占用
		boolean b = multipleChoiceService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "题号已占用!");
		}
	}
}
