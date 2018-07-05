package com.exam.online.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.Student;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.StudentService;
import com.exam.online.util.DataUtil;
import com.exam.online.util.ValidateUtil;



@Controller
@Scope("prototype")
public class StudentListAction extends BaseAction<Student>{

	private static final long serialVersionUID = -3571468773194661496L;

	private List<Student> studentList;

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Resource
	private StudentService studentService;
	
	
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

	public String doAdd() {
		model.setPassword(DataUtil.md5(model.getPassword()));
		model.setStatus(0);
		studentService.saveEntity(model);
		return SUCCESS ;
	}
	//修改学生
	@SkipValidation
	public String modifyStu() {
		this.model = studentService.getStudent(sid);
		return "modifyStudent";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdateStu(){
		studentService.saveOrUpdateStu(model);
		inputPage = "/manager/modifyStu.jsp";
		return "findAllStuAction" ;
	}
	
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	//管理员查询所有学生
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String findStudentList() {
		//this.studentList = studentService.getAllStudent();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = studentService.getAllStudentByPage(page);
		page = result.getPage();
		this.studentList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "toStudentListPage";
	}
	
	/**
	 * 教师查看学生信息
	 */
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String findStudents() {
			//this.studentList = studentService.getAllStudent();
			Page page = new Page();
			page.setCurrentPage(this.getCurrentPage());
			page.setEveryPage(10);
			Result result = studentService.getAllStudentByPage(page);
			page = result.getPage();
			this.studentList = result.getList();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", page);
			return "toStudentList";
	}
	
	private String query;
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	//按条件查询分页显示
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String queryStudent() {
		if ("all".equals(type) || query == null || "".equals(query)) {
			//this.studentList = studentService.getAllStudent();
			Page page = new Page();
			page.setCurrentPage(this.getCurrentPage());
			page.setEveryPage(10);
			Result result = studentService.getAllStudentByPage(page);
			page = result.getPage();
			this.studentList = result.getList();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", page);
			return "toStudentList";
		} else {
			Page page = new Page();
			page.setCurrentPage(this.getCurrentPage());
			page.setEveryPage(10);
			Result result = studentService.getAllStudentByPage(page,type,query);
			page = result.getPage();
			this.studentList = result.getList();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", page);
		}
		return "toStudentList";
	}
	
	@SkipValidation
	public String deleteStu() {
		studentService.deleteStudent(sid);
		return "findAllStuAction";
	}
	

	public void validate() {
		inputPage = "/manager/addStudent.jsp";
		/*System.out.println("number="+model.getNumber());
		System.out.println("password="+model.getPassword());
		System.out.println("name="+model.getName());*/
		//1.非空
		if(!ValidateUtil.isValid(model.getNumber())){
			addFieldError("number", "学号是必填项!");
		}
		if(!ValidateUtil.isValid(model.getPassword())){
			addFieldError("password", "密码是必填项!");
		}
		if(!ValidateUtil.isValid(model.getName())){
			addFieldError("name", "姓名是必填项!");
		}
		if(!ValidateUtil.isValid(model.getClas())){
			addFieldError("clas", "班级是必填项!");
		}
		if(!ValidateUtil.isValid(model.getMajor())){
			addFieldError("major", "专业是必填项!");
		}
		if (model.getNumber().length() > 20) {
			addFieldError("number", "学号不能超过20位");
		}
		if(model.getPassword().length() > 20){
			addFieldError("password", "密码不能超过20位");
		}
		if(model.getName().length() > 10){
			addFieldError("name", "请输入真实姓名");
		}
		if(model.getMajor().length() > 20){
			addFieldError("major", "格式如下例：软件工程");
		}
		if(model.getClas().length() > 20){
			addFieldError("clas", "格式如下例：软件二班");
		}
		if(this.hasErrors()){
			return ;
		}
		//3.number是否占用
		boolean b = studentService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "学号已占用!");
		}
		
	}
}
