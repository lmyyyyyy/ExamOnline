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

import com.exam.online.domain.SingleChoice;
import com.exam.online.domain.Subjects;
import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.SingleChoiceService;
import com.exam.online.service.SubjectsService;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class SingleChoiceAction extends BaseAction<SingleChoice>{

	private static final long serialVersionUID = 3722486050218295875L;

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
	
	private String dxNumber;
	
	public String getDxNumber() {
		return dxNumber;
	}

	public void setDxNumber(String dxNumber) {
		this.dxNumber = dxNumber;
	}

	public List<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}
	
	private List<SingleChoice> singleChoiceList;

	public List<SingleChoice> getSingleChoiceList() {
		return singleChoiceList;
	}

	public void setSingleChoiceList(List<SingleChoice> singleChoiceList) {
		this.singleChoiceList = singleChoiceList;
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
	private SingleChoiceService singleChoiceService;
	
	@SkipValidation
	
	public String toAddSingle() {
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyymmddHHMMss");
	    String date=sdf.format(new Date());
		this.dxNumber = "DX"+date;
		this.subjectsList = subjectsService.getAllSubjects();
		return "toAddSingle";
	}
	
	//获取所有单选
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String allSingleChoice() {
		//this.singleChoiceList = singleChoiceService.getAllSingleChoice();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = singleChoiceService.getAllSingleChoiceByPage(page);
		page = result.getPage();
		this.singleChoiceList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "findAllSingleChoice";
	}
	
	@SkipValidation
	public String delete() {
		singleChoiceService.delete(sid);
		return "deleteSingleChoice";
	}
	//修改
	@SkipValidation
	public String modify() {
		this.model = singleChoiceService.getSingleChoice(sid);
		return "modifySingleChoice";
	}
	
	//查看
	@SkipValidation
	public String view() {
		this.model = singleChoiceService.getSingleChoice(sid);
		return "viewSingleChoice";
	}
	
	/**
	 * 保存/更新
	 */

	@SkipValidation
	public String saveOrUpdate(){
		singleChoiceService.saveOrUpdate(model);
		inputPage = "/teacher/modifySingleChoice.jsp";
		return "tofindAllSingleChoice" ;
	}
	
	//添加
	public String doAdd() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Teacher teacher = (Teacher) s.getAttribute("teacher");
		model.setAuthor(teacher.getName());
		singleChoiceService.saveEntity(model);
		return SUCCESS;
	}
	//验证
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
		inputPage = "/teacher/addSingleChoice.jsp";
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
		boolean b = singleChoiceService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "题号已占用!");
		}
	}
}
