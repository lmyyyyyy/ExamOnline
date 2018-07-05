package com.exam.online.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.ProgramAnswers;
import com.exam.online.domain.Results;
import com.exam.online.domain.Student;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.ProgramAnswersService;
import com.exam.online.service.ResultsService;

@Controller
@Scope("prototype")
public class ResultsAction extends BaseAction<Results>{

	private static final long serialVersionUID = -3460852654852746465L;
	
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

	private Integer allScore;

	public Integer getAllScore() {
		return allScore;
	}

	public void setAllScore(Integer allScore) {
		this.allScore = allScore;
	}
	
	private Integer score;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	private String code;
	private String count;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	private List<Results> resultsList;
	
	public List<Results> getResultsList() {
		return resultsList;
	}
	
	public void setResultsList(List<Results> resultsList) {
		this.resultsList = resultsList;
	}
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@Resource
	private ResultsService resultsService;
	@Resource
	private ProgramAnswersService programAnswersService;
	
	@SuppressWarnings("unchecked")
	public String toQueryResults() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Student student = (Student) s.getAttribute("student");
		String snumber = student.getNumber();
		//this.resultsList = resultsService.getResults(snumber);
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = resultsService.getResultsByPage(page,snumber);
		page = result.getPage();
		this.resultsList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "toQueryResults";
	}
	
	@SuppressWarnings("unchecked")
	public String allResults() {
		//this.resultsList = resultsService.getAllResults();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = resultsService.getAllResultsByPage(page);
		page = result.getPage();
		this.resultsList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "allResults";
	}
	
	public String updateScore() {
		String enumber = model.getEnumber();
		String snumber = model.getSnumber();
		inputPage = "/teacher/error.jsp";
		if (allScore == null) {
			return INPUT;
		}
		if (allScore > score) {
			return INPUT;
		}
		
		Results results = resultsService.isRegisted(enumber, snumber);
		
		if (results == null) {
			return INPUT;
		}
		int id = results.getId();
		results.setId(id);
		results.setProgramScore(allScore);
		results.setTotalScore(allScore);
		
		resultsService.saveOrUpdateEntity(results);
		ProgramAnswers programAnswers = programAnswersService.getProgramAnswers(enumber, snumber);
		if (programAnswers.getStatus() == 0) {
			programAnswers.setStatus(1);
		}
		programAnswersService.saveOrUpdateEntity(programAnswers);
		return "updateScore";
	}
	
	public String modify() {
		this.model = resultsService.getEntity(sid);
		return "modify";
	}
	
	public String saveOrUpdate() {
		resultsService.saveOrUpdateEntity(model);
		return "saveOrUpdate";
	}
	
	public String view() {
		this.model = resultsService.getEntity(sid);
		return "view";
	}
	
	private String query;
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@SuppressWarnings("unchecked")
	public String queryResults() {
		if ("all".equals(type) || query == null || "".equals(query)) {
			//this.resultsList = resultsService.getAllResults();
			Page page = new Page();
			page.setCurrentPage(this.getCurrentPage());
			page.setEveryPage(10);
			Result result = resultsService.getAllResultsByPage(page);
			page = result.getPage();
			this.resultsList = result.getList();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", page);
			return "allResults";
		} else {
			Page page = new Page();
			page.setCurrentPage(this.getCurrentPage());
			page.setEveryPage(10);
			Result result = resultsService.getAllResultsByPage(page,type,query);
			page = result.getPage();
			this.resultsList = result.getList();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("page", page);
			//this.resultsList = resultsService.getAllResults(type,query);
		}
		return "allResults";
	}
}
