package com.exam.online.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.exam.online.domain.PaperItem;
import com.exam.online.domain.Program;
import com.exam.online.domain.ProgramAnswers;
import com.exam.online.service.PaperItemService;
import com.exam.online.service.ProgramAnswersService;
import com.exam.online.service.ProgramService;

@Controller
@Scope("prototype")
public class ProgramAnswersAction extends BaseAction<ProgramAnswers>{

	private static final long serialVersionUID = -8418421943052751444L;

	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	private List<ProgramAnswers> paList;
	
	public List<ProgramAnswers> getPaList() {
		return paList;
	}

	public void setPaList(List<ProgramAnswers> paList) {
		this.paList = paList;
	}
	
	private List<Program> programList = new ArrayList<Program>();

	public List<Program> getProgramList() {
		return programList;
	}

	public void setProgramList(List<Program> programList) {
		this.programList = programList;
	}

	@Resource
	private ProgramAnswersService programAnswersService;
	@Resource
	private PaperItemService paperItemService;
	@Resource
	private ProgramService programService;
	
	public String findAll() {
		this.paList = programAnswersService.getAllPAnswers();
		return "findAll";
	}
	
	public String modify() {
		this.model = programAnswersService.getEntity(sid);
		String enumber = model.getEnumber();
		System.out.println("enumber"+enumber);
		List<PaperItem> programsList = paperItemService.getProgram(enumber,"program");
		Iterator<PaperItem> programIt = programsList.iterator();
		while (programIt.hasNext()) {
			PaperItem paperItem = programIt.next();
			Program program = programService.getProgram(paperItem.getQnumber());
			this.programList.add(program);
		}
		return "modify";
	}
}
