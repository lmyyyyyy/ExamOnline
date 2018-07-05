package com.exam.online.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.exam.online.domain.Blank;
import com.exam.online.domain.Judgment;
import com.exam.online.domain.MultipleChoice;
import com.exam.online.domain.Paper;
import com.exam.online.domain.PaperItem;
import com.exam.online.domain.Program;
import com.exam.online.domain.ProgramAnswers;
import com.exam.online.domain.Results;
import com.exam.online.domain.SingleChoice;
import com.exam.online.domain.Student;
import com.exam.online.domain.Subjects;
import com.exam.online.domain.Teacher;
import com.exam.online.page.Page;
import com.exam.online.page.Result;
import com.exam.online.service.BlankService;
import com.exam.online.service.JudgmentService;
import com.exam.online.service.MultipleChoiceService;
import com.exam.online.service.PaperItemService;
import com.exam.online.service.PaperService;
import com.exam.online.service.ProgramAnswersService;
import com.exam.online.service.ProgramService;
import com.exam.online.service.ResultsService;
import com.exam.online.service.SingleChoiceService;
import com.exam.online.service.SubjectsService;
import com.exam.online.util.SplitUtil;
import com.exam.online.util.ValidateUtil;

@Controller
@Scope("prototype")
public class PaperAction extends BaseAction<Paper>{

	private static final long serialVersionUID = -2834656343756136793L;
	
	private List<Subjects> subjectsList;
	
	private Integer sid;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	private String result = "";
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	private String code;
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String inputPage;
	
	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}
	
	private String sjNumber;

	public String getSjNumber() {
		return sjNumber;
	}

	public void setSjNumber(String sjNumber) {
		this.sjNumber = sjNumber;
	}

	public List<Subjects> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subjects> subjectsList) {
		this.subjectsList = subjectsList;
	}
	
	private List<Paper> paperList;

	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}
	
	private Integer score;
	private String enumber;
	private String qnumber;
	private String type;
	private Integer singleScore;
	private Integer multipleScore;
	private Integer blankScore;
	private Integer judgmentScore;
	private Integer programScore;
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getEnumber() {
		return enumber;
	}

	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}

	public String getQnumber() {
		return qnumber;
	}

	public void setQnumber(String qnumber) {
		this.qnumber = qnumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSingleScore() {
		return singleScore;
	}

	public void setSingleScore(Integer singleScore) {
		this.singleScore = singleScore;
	}

	public Integer getMultipleScore() {
		return multipleScore;
	}

	public void setMultipleScore(Integer multipleScore) {
		this.multipleScore = multipleScore;
	}

	public Integer getBlankScore() {
		return blankScore;
	}

	public void setBlankScore(Integer blankScore) {
		this.blankScore = blankScore;
	}

	public Integer getJudgmentScore() {
		return judgmentScore;
	}

	public void setJudgmentScore(Integer judgmentScore) {
		this.judgmentScore = judgmentScore;
	}

	public Integer getProgramScore() {
		return programScore;
	}

	public void setProgramScore(Integer programScore) {
		this.programScore = programScore;
	}

	public PaperService getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}
	
	private List<PaperItem> paperItemList;

	public List<PaperItem> getPaperItemList() {
		return paperItemList;
	}

	public void setPaperItemList(List<PaperItem> paperItemList) {
		this.paperItemList = paperItemList;
	}
	
	

	@Resource
	private SubjectsService subjectsService;
	@Resource
	private PaperService paperService;
	@Resource
	private PaperItemService paperItemService;
	@Resource
	private SingleChoiceService singleChoiceService;
	@Resource
	private MultipleChoiceService multipleChoiceService;
	@Resource
	private BlankService blankService;
	@Resource
	private JudgmentService judgmentService;
	@Resource
	private ProgramService programService;
	@Resource
	private ResultsService resultsService;
	
	@SkipValidation
	public String toAddPaper() {
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyymmddHHMMss");
	    String date=sdf.format(new Date());//格式化当前时间
		this.sjNumber = "SJ"+date;//组成试卷唯一标识
		this.subjectsList = subjectsService.getAllSubjects();//获取所有的学科，为了前台迭代
		return "toAddPaper";
	}
	
	@SkipValidation
	public String toAddProgramPaper() {
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	    String date=sdf.format(new Date());
		this.sjNumber = "SJ"+date;
		this.subjectsList = subjectsService.getAllSubjects();
		return "toAddProgramPaper";
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
	public String allPaper() {
		//this.paperList = paperService.getAllPaper();
		Page page = new Page();//定义一个page对象
		page.setCurrentPage(this.getCurrentPage());//给当前页赋值
		page.setEveryPage(10);//给每页赋值
		Result result = paperService.getAllPaperByPage(page);//获得分页的结果集
		page = result.getPage();//从结果集中获得分页信息
		this.paperList = result.getList();//将结果集中的试卷list存在属性中
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);//将page对象存放在request中
		return "findAllPaper";
	}
	
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String mallPaper() {
		//this.paperList = paperService.getAllPaper();
		Page page = new Page();
		page.setCurrentPage(this.getCurrentPage());
		page.setEveryPage(10);
		Result result = paperService.getAllPaperByPage(page);
		page = result.getPage();
		this.paperList = result.getList();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("page", page);
		return "mfindAllPaper";
	}
	
	@SkipValidation
	public String myPaper() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Teacher teacher = (Teacher) s.getAttribute("teacher");//获取当前session的对象
		this.paperList = paperService.getAllPaper(teacher.getName());//根据在线人姓名查询
		return "findMyPaper";
	}
	//考试信息
	@SkipValidation
	public String currentPaper() {
		this.paperList = paperService.getCurrentPaper();
		return "findCurrentPaper";
	}
	
	@SkipValidation
	public String mcurrentPaper() {
		this.paperList = paperService.getCurrentPaper();
		return "mfindCurrentPaper";
	}
	
	/**
	 * 学生查询的当前可以考试的试卷
	 * @return
	 */
	@SkipValidation
	public String scurrentPaper() {
		HttpSession s = ServletActionContext.getRequest().getSession();
		Student student = (Student) s.getAttribute("student");
		//根据当前student的学号获取成绩表中的所有成绩
		List<Results> list = resultsService.getAllResults(student.getNumber());
		List<String> enumberList = new ArrayList<String>();
		//获取成绩list中的试卷号
		Iterator<Results> resultsIt = list.iterator();
		while (resultsIt.hasNext()) {
			Results results = resultsIt.next();
			enumberList.add(results.getEnumber());//将试卷号存在enumberList中
		}
		this.paperList = paperService.getCurrentPaper(enumberList);//根据enubmerList拼接hql语句，获取符合条件的试卷列表
		return "sfindCurrentPaper";
	}
	
	@SkipValidation
	public String delete() {
		paperService.delete(sid);
		return "deletePaper";
	}
	
	@SkipValidation
	public String mdelete() {
		paperService.delete(sid);
		return "mdeletePaper";
	}
	
	@SkipValidation
	public String modify() {
		this.model = paperService.getPaper(sid);
		return "modifyPaper";
	}
	
	
	private List<SingleChoice> singleList = new ArrayList<SingleChoice>();//存放单选题
	private List<MultipleChoice> multipleList = new ArrayList<MultipleChoice>();
	private List<Blank> blankList = new ArrayList<Blank>();
	private List<Judgment> judgmentList = new ArrayList<Judgment>();
	private List<Program> programList = new ArrayList<Program>();

	public List<Program> getProgramList() {
		return programList;
	}

	public void setProgramList(List<Program> programList) {
		this.programList = programList;
	}

	public List<SingleChoice> getSingleList() {
		return singleList;
	}

	public void setSingleList(List<SingleChoice> singleList) {
		this.singleList = singleList;
	}

	public List<MultipleChoice> getMultipleList() {
		return multipleList;
	}

	public void setMultipleList(List<MultipleChoice> multipleList) {
		this.multipleList = multipleList;
	}

	public List<Blank> getBlankList() {
		return blankList;
	}

	public void setBlankList(List<Blank> blankList) {
		this.blankList = blankList;
	}

	public List<Judgment> getJudgmentList() {
		return judgmentList;
	}

	public void setJudgmentList(List<Judgment> judgmentList) {
		this.judgmentList = judgmentList;
	}
	private int singleChoiceScore;
	private int multipleChoiceScore;
	private int blanksScore;
	private int judgmentsScore;
	private int programsScore;
	
	public int getProgramsScore() {
		return programsScore;
	}

	public void setProgramsScore(int programsScore) {
		this.programsScore = programsScore;
	}

	public int getMultipleChoiceScore() {
		return multipleChoiceScore;
	}

	public void setMultipleChoiceScore(int multipleChoiceScore) {
		this.multipleChoiceScore = multipleChoiceScore;
	}

	public int getBlanksScore() {
		return blanksScore;
	}

	public void setBlanksScore(int blanksScore) {
		this.blanksScore = blanksScore;
	}

	public int getJudgmentsScore() {
		return judgmentsScore;
	}

	public void setJudgmentsScore(int judgmentsScore) {
		this.judgmentsScore = judgmentsScore;
	}

	public int getSingleChoiceScore() {
		return singleChoiceScore;
	}

	public void setSingleChoiceScore(int singleChoiceScore) {
		this.singleChoiceScore = singleChoiceScore;
	}
	
	@Transactional
	@SkipValidation
	public String view() {
		this.model = paperService.getPaper(sid);//根据id获取试卷实体
		if (model != null) {
			String paperNumber = model.getNumber();//获取当前试卷的试卷号
			List<PaperItem> singleChoiceList = paperItemService.getSingleChoice(paperNumber,"singleChoice");//根据试卷号，和题的类型，去试卷子表中查询所有符合条件的实体
			List<PaperItem> multipleChoiceList = paperItemService.getMultipleChoice(paperNumber,"multipleChoice");
			List<PaperItem> blanksList = paperItemService.getBlank(paperNumber,"blank");
			List<PaperItem> judgmentsList = paperItemService.getJudgment(paperNumber,"judgment");
			List<PaperItem> programsList = paperItemService.getJudgment(paperNumber,"program");
			
			HttpServletRequest request = ServletActionContext.getRequest();
			
			request.setAttribute("paperList", model);
	        
			/*Iterator<PaperItem> singleIt =  singleChoiceList.iterator();
			while (singleIt.hasNext()) {
				PaperItem paperItem = singleIt.next();
				SingleChoice singleChoice = singleChoiceService.getSingleChoice(paperItem.getQnumber());
				System.out.println(singleChoice.getNumber());
				this.singleList.add(singleChoice);
			}
			*/
			for (ListIterator<PaperItem> singleIt = singleChoiceList.listIterator(); singleIt.hasNext();) {//迭代单选类型的子条目
				PaperItem paperItem = singleIt.next();
				this.singleChoiceScore = paperItem.getScore();//获取当前试卷中当前类型的分数
				SingleChoice singleChoice = singleChoiceService.getSingleChoice(paperItem.getQnumber());//根据每个单选题的题号去单选题表中查询所有单选
				this.singleList.add(singleChoice);//将符合条件的单选题都存到singleList中,以便在前台迭代
			}
			
			Iterator<PaperItem> multipleIt = multipleChoiceList.iterator();
			while (multipleIt.hasNext()) {
				PaperItem paperItem = multipleIt.next();
				this.multipleChoiceScore = paperItem.getScore();
				MultipleChoice multipleChoice = multipleChoiceService.getMultipleChoice(paperItem.getQnumber());
				this.multipleList.add(multipleChoice);
			}
			
			Iterator<PaperItem> blankIt = blanksList.iterator();
			while (blankIt.hasNext()) {
				PaperItem paperItem = blankIt.next();
				this.blanksScore = paperItem.getScore();
				Blank blank = blankService.getBlank(paperItem.getQnumber());
				this.blankList.add(blank);
			}
			
			Iterator<PaperItem> judgmentIt = judgmentsList.iterator();
			while (judgmentIt.hasNext()) {
				PaperItem paperItem = judgmentIt.next();
				this.judgmentsScore = paperItem.getScore();
				Judgment judgment = judgmentService.getJudgment(paperItem.getQnumber());
				this.judgmentList.add(judgment);
			}
			
			Iterator<PaperItem> programIt = programsList.iterator();
			while (programIt.hasNext()) {
				PaperItem paperItem = programIt.next();
				this.programsScore = paperItem.getScore();
				Program program = programService.getProgram(paperItem.getQnumber());
				this.programList.add(program);
			}
			
	      /*  request.setAttribute("singleList", singleList);
	        request.setAttribute("multipleList", multipleList);
	        request.setAttribute("blankList", blankList);
	        request.setAttribute("judgmentList", judgmentList);*/
	        return "viewPaper";
		}
		inputPage = "/teacher/viewPaper";
		return INPUT;
	}
	
	@Transactional
	@SkipValidation
	public String sview() {
		this.model = paperService.getPaper(sid);
		if (model != null) {
			String paperNumber = model.getNumber();
			this.score = model.getScore();
			List<PaperItem> singleChoiceList = paperItemService.getSingleChoice(paperNumber,"singleChoice");
			List<PaperItem> multipleChoiceList = paperItemService.getMultipleChoice(paperNumber,"multipleChoice");
			List<PaperItem> blanksList = paperItemService.getBlank(paperNumber,"blank");
			List<PaperItem> judgmentsList = paperItemService.getJudgment(paperNumber,"judgment");
			List<PaperItem> programsList = paperItemService.getProgram(paperNumber,"program");
			
			HttpServletRequest request = ServletActionContext.getRequest();
			
			request.setAttribute("paperList", model);
	        
			/*Iterator<PaperItem> singleIt =  singleChoiceList.iterator();
			while (singleIt.hasNext()) {
				PaperItem paperItem = singleIt.next();
				SingleChoice singleChoice = singleChoiceService.getSingleChoice(paperItem.getQnumber());
				System.out.println(singleChoice.getNumber());
				this.singleList.add(singleChoice);
			}
			*/
			for (ListIterator<PaperItem> singleIt = singleChoiceList.listIterator(); singleIt.hasNext();) {
				PaperItem paperItem = singleIt.next();
				this.singleChoiceScore = paperItem.getScore();
				SingleChoice singleChoice = singleChoiceService.getSingleChoice(paperItem.getQnumber());
				this.singleList.add(singleChoice);
			}
			
			Iterator<PaperItem> multipleIt = multipleChoiceList.iterator();
			while (multipleIt.hasNext()) {
				PaperItem paperItem = multipleIt.next();
				this.multipleChoiceScore = paperItem.getScore();
				MultipleChoice multipleChoice = multipleChoiceService.getMultipleChoice(paperItem.getQnumber());
				this.multipleList.add(multipleChoice);
			}
			
			Iterator<PaperItem> blankIt = blanksList.iterator();
			while (blankIt.hasNext()) {
				PaperItem paperItem = blankIt.next();
				this.blanksScore = paperItem.getScore();
				Blank blank = blankService.getBlank(paperItem.getQnumber());
				this.blankList.add(blank);
			}
			
			Iterator<PaperItem> judgmentIt = judgmentsList.iterator();
			while (judgmentIt.hasNext()) {
				PaperItem paperItem = judgmentIt.next();
				this.judgmentsScore = paperItem.getScore();
				Judgment judgment = judgmentService.getJudgment(paperItem.getQnumber());
				this.judgmentList.add(judgment);
			}
			
			Iterator<PaperItem> programIt = programsList.iterator();
			while (programIt.hasNext()) {
				PaperItem paperItem = programIt.next();
				this.programsScore = paperItem.getScore();
				Program program = programService.getProgram(paperItem.getQnumber());
				this.programList.add(program);
			}
			
	        /*request.setAttribute("singleList", singleList);
	        request.setAttribute("multipleList", multipleList);
	        request.setAttribute("blankList", blankList);
	        request.setAttribute("judgmentList", judgmentList);*/
	        return "sviewPaper";
		}
		inputPage = "/student/sviewPaper";
		return INPUT;
	}
	
	
	/**
	 * 计算成绩，保存答案和成绩
	 * @return
	 */
	@Transactional
	@SkipValidation
	public String saveAnswer() {
		String singlechoicenumber = "";
		String studentSingleAnswer = "";
		String allSingleAnswer = "";
		int singleCount = 0;
		singleChoiceScore = 0;
		String singleAnswers = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		this.model = paperService.getPaper(sid);//根据当前试卷id获取当前试卷信息
		
		String paperNumber = model.getNumber();//获取当前试卷的卷号
		this.score = model.getScore();//获取当前试卷的总分
		String subject = model.getSubject();//获取当前试卷的科目
		String ename = model.getName();//获取当前试卷的名称
		HttpSession s = ServletActionContext.getRequest().getSession();
		Student student = (Student) s.getAttribute("student");//获取当前session实体
		String sname = student.getName();
		String snumber = student.getNumber();
		String sclas = student.getClas();
		Results result1 = resultsService.isRegisted(paperNumber, snumber);//根据试卷号和学号去成绩表中验证是否已经回答过了
		if (result1 != null) {//如果回答过了，跳到错误页面
			inputPage = "/student/editor.jsp";
			return INPUT;
		}
		List<PaperItem> singleChoiceList = paperItemService.getSingleChoice(paperNumber,"singleChoice");//根据卷号和题型获取当前题型的集合
		List<PaperItem> multipleChoiceList = paperItemService.getMultipleChoice(paperNumber,"multipleChoice");//根据卷号和题型获取当前题型的集合
		List<PaperItem> blanksList = paperItemService.getBlank(paperNumber,"blank");//根据卷号和题型获取当前题型的集合
		List<PaperItem> judgmentsList = paperItemService.getJudgment(paperNumber,"judgment");//根据卷号和题型获取当前题型的集合
		
		//---------------------------------我是万恶的分割线-----------------------------------------------
		//判断单选题的分数start
		for (ListIterator<PaperItem> singleIt = singleChoiceList.listIterator(); singleIt.hasNext();) {//迭代试卷子条目中的单选题
			PaperItem paperItem = singleIt.next();
			this.singleChoiceScore = paperItem.getScore();//获得单选题的分数
			SingleChoice singleChoice = singleChoiceService.getSingleChoice(paperItem.getQnumber());//根据子条目中的单选题号，去查询单选表获取所有符合条件的单选题
			singleList.add(singleChoice);//添加到singleList中，以便为了比对答案
		}
		
		Iterator<SingleChoice> singleChoiceIt = singleList.iterator();//迭代查询出来的所有单选题
		List<String> singleNumber = new ArrayList<String>();//用于存储单选题的题号
		List<String> singleAnswer = new ArrayList<String>();//用于存储单选题的正确答案
		while(singleChoiceIt.hasNext()) {
			SingleChoice singleChoice = singleChoiceIt.next();
			singleNumber.add(singleChoice.getNumber());//将题号迭代添加
			singleAnswer.add(singleChoice.getAnswer());//将正确答案迭代添加
		}
		Iterator<String> singlenumber = singleNumber.iterator();//迭代单选题号
		while (singlenumber.hasNext()) {
			singlechoicenumber = singlenumber.next();
			studentSingleAnswer = request.getParameter(singlechoicenumber);//根据迭代中的单选题号，去前台获取与题号相同的name属性中的值
			if (studentSingleAnswer == null || "".equals(studentSingleAnswer)) {//判断如果获取到的值是null 说明本道题当前学生没有选
				studentSingleAnswer = "!";//那么将本题的学生答案存为!
			}
			allSingleAnswer += studentSingleAnswer + "_";//每获取到一个答案，就用_连接
		}
		String[] singleArr = SplitUtil.splitString(allSingleAnswer);//定义一个工具类，用于切分字符串，以数组的形式返回
		Iterator<String> singleanswer = singleAnswer.iterator();//迭代正确答案
		int singlei = 0;//定义一个标志数
		while (singleanswer.hasNext()) {
			singleAnswers = singleanswer.next();
			if (singleAnswers.equals(singleArr[singlei++].toLowerCase())) {//判断，如果获取的答案与正确答案相符，值就加一
				++singleCount;
			}
		}
		int singleScore = singleCount * singleChoiceScore;//计算正确的数量与每题的分数相乘，结果为该类型题的总分
		System.out.println("单选题得分="+singleScore);
		//判断单选题的分数end
		//---------------------------------我是万恶的分割线-----------------------------------------------
		//判断多选题的分数start
		String multiplechoicenumber = "";
		String[] studentMultipleAnswer;
		String allMultipleAnswer = "";
		int multipleCount = 0;
		String studentMultiple = "";
		String multipleAnswers = "";
		multipleChoiceScore = 0;
		Iterator<PaperItem> multipleIt = multipleChoiceList.iterator();//迭代多选的试卷子条目
		while (multipleIt.hasNext()) {
			PaperItem paperItem = multipleIt.next();
			this.multipleChoiceScore = paperItem.getScore();//获取当前试卷多选的分数
			MultipleChoice multipleChoice = multipleChoiceService.getMultipleChoice(paperItem.getQnumber());//根据子条目中多选的题号获取所有多选
			multipleList.add(multipleChoice);//将所有多选保存进multipleList
		}
		Iterator<MultipleChoice> multipleChoiceIt = multipleList.iterator();//迭代当前试卷的所有多选
		List<String> multipleNumber = new ArrayList<String>();//创建一个list用于存放当前试卷的所有多选的题号
		List<String> multipleAnswer = new ArrayList<String>();//创建一个list用于存放当前试卷的所有多选的答案
		while(multipleChoiceIt.hasNext()) {
			MultipleChoice multipleChoice = multipleChoiceIt.next();
			multipleNumber.add(multipleChoice.getNumber());//存放题号
			multipleAnswer.add(multipleChoice.getAnswer());//存放答案
		}
		
		Iterator<String> multiplenumber = multipleNumber.iterator();//迭代题号
		while (multiplenumber.hasNext()) {
			multiplechoicenumber = multiplenumber.next();
			studentMultipleAnswer = request.getParameterValues(multiplechoicenumber);//根据当前题号获取前台的值
			studentMultiple = SplitUtil.splitArr(studentMultipleAnswer);
			if (studentMultiple == null || "".equals(studentMultiple)) {//判断前台的值是否为空，如果为空用！表示
				studentMultiple = "!";
			}
			allMultipleAnswer += studentMultiple + "_";//将所有前台获取的值用_连接
		}
		String[] multipleArr = SplitUtil.splitString(allMultipleAnswer);//根据_切取所有的答案，存放在数组中
		Iterator<String> multipleanswer = multipleAnswer.iterator();//迭代正确答案
		int multiplei = 0;//定义该学生正确的数量
		while (multipleanswer.hasNext()) {
			multipleAnswers = multipleanswer.next();
			if (multipleAnswers.equals(multipleArr[multiplei++].toLowerCase())) {//判断，如果从前台获取的值与正确答案相等，数量+1
				++multipleCount;
			}
		}
		int multipleScore = multipleCount * multipleChoiceScore;//计算该类型题最后的得分
		System.out.println("多选题得分="+multipleScore);
		//判断多选题的分数end
		//---------------------------------我是万恶的分割线-----------------------------------------------
		//判断填空题的分数start
		String blankchoicenumber = "";
		String studentBlankAnswer = "";
		String allBlankAnswer = "";
		int blankCount = 0;
		String blankAnswers = "";
		blankScore = 0;
		Iterator<PaperItem> blankIt = blanksList.iterator();//迭代填空的试卷子条目
		while (blankIt.hasNext()) {
			PaperItem paperItem = blankIt.next();
			this.blankScore = paperItem.getScore();//获取当前试卷填空的分数
			Blank blank = blankService.getBlank(paperItem.getQnumber());//根据子条目中填空的题号获取所有填空
			blankList.add(blank);//将所有填空保存进multipleList
		}
		Iterator<Blank> blanksIt = blankList.iterator();//迭代当前试卷的所有填空
		List<String> blankNumber = new ArrayList<String>();//创建一个list用于存放当前试卷的所有填空的题号
		List<String> blankAnswer = new ArrayList<String>();//创建一个list用于存放当前试卷的所有填空的答案
		while(blanksIt.hasNext()) {
			Blank blank = blanksIt.next();
			blankNumber.add(blank.getNumber());//存放题号
			blankAnswer.add(blank.getAnswer());//存放答案
		}
		
		Iterator<String> blanknumber = blankNumber.iterator();//迭代题号
		while (blanknumber.hasNext()) {
			blankchoicenumber = blanknumber.next();
			studentBlankAnswer = request.getParameter(blankchoicenumber);//根据当前题号获取前台的值
			if (studentBlankAnswer == null || "".equals(studentBlankAnswer.trim())) {//判断前台的值是否为空，如果为空用！表示
				studentBlankAnswer = "!";
			}
			allBlankAnswer += studentBlankAnswer.trim() + "_";//将所有前台获取的值用_连接
		}
		String[] blankArr = SplitUtil.splitString(allBlankAnswer);//根据_切取所有的答案，存放在数组中
		Iterator<String> blankanswer = blankAnswer.iterator();//迭代正确答案
		int blanki = 0;//定义该学生正确的数量
		while (blankanswer.hasNext()) {
			blankAnswers = blankanswer.next();
			if (blankAnswers.equals(blankArr[blanki++].trim())) {//判断，如果从前台获取的值与正确答案相等，数量+1
				++blankCount;
			}
		}
		int blanksScore = blankCount * blankScore;//计算该类型题最后的得分
		System.out.println("填空题得分="+blanksScore);
		//判断填空题的分数end
		//---------------------------------我是万恶的分割线-----------------------------------------------
		//判断判断题的分数start
		String judgmentchoicenumber = "";
		String studentJudgmentAnswer = "";
		String allJudgmentAnswer = "";
		int judgmentCount = 0;
		String judgmentAnswers = "";
		judgmentScore = 0;
		Iterator<PaperItem> judgmentIt = judgmentsList.iterator();//迭代判断的试卷子条目
		while (judgmentIt.hasNext()) {
			PaperItem paperItem = judgmentIt.next();
			this.judgmentScore = paperItem.getScore();//获取当前试卷判断的分数
			Judgment judgment = judgmentService.getJudgment(paperItem.getQnumber());//根据子条目中判断的题号获取所有判断
			judgmentList.add(judgment);//将所有填空保存进judgmentList
		}
		Iterator<Judgment> judgmentsIt = judgmentList.iterator();//迭代当前试卷的所有判断
		List<String> judgmentNumber = new ArrayList<String>();//创建一个list用于存放当前试卷的所有判断的题号
		List<String> judgmentAnswer = new ArrayList<String>();//创建一个list用于存放当前试卷的所有判断的答案
		while(judgmentsIt.hasNext()) {
			Judgment judgment = judgmentsIt.next();
			judgmentNumber.add(judgment.getNumber());//存放题号
			judgmentAnswer.add(judgment.getAnswer());//存放正确答案
		}
		
		Iterator<String> judgmentnumber = judgmentNumber.iterator();//迭代题号
		while (judgmentnumber.hasNext()) {
			judgmentchoicenumber = judgmentnumber.next();
			studentJudgmentAnswer = request.getParameter(judgmentchoicenumber);//根据当前题号获取前台的值
			if (studentJudgmentAnswer == null || "".equals(studentJudgmentAnswer)) {//判断前台的值是否为空，如果为空用！表示
				studentJudgmentAnswer = "!";
			}
			allJudgmentAnswer += studentJudgmentAnswer + "_";//将所有前台获取的值用_连接
		}
		String[] judgmentArr = SplitUtil.splitString(allJudgmentAnswer);//根据_切取所有的答案，存放在数组中
		Iterator<String> judgmentanswer = judgmentAnswer.iterator();//迭代正确答案
		int judgmenti = 0;//定义该学生正确的数量
		while (judgmentanswer.hasNext()) {
			judgmentAnswers = judgmentanswer.next();
			if (judgmentAnswers.equals(judgmentArr[judgmenti++])) {//判断，如果从前台获取的值与正确答案相等，数量+1
				++judgmentCount;
			}
		}
		int judgmentsScore = judgmentCount * judgmentScore;//计算该类型题最后的得分
		System.out.println("判断题得分="+judgmentsScore);
		//判断判断题的分数end
		//---------------------------------我是万恶的分割线-----------------------------------------------
		//计算总分
		int totalScore = singleScore + multipleScore + blanksScore +judgmentsScore;
		System.out.println("总分：" + totalScore);
		//保存结果
		Results results = new Results();
		results.setSubjcet(subject);
		results.setSnumber(snumber);
		results.setSname(sname);
		results.setSclas(sclas);
		results.setEnumber(paperNumber);
		results.setEname(ename);
		results.setSingleScore(singleScore);
		results.setMultipleScore(multipleScore);
		results.setBlankScore(blanksScore);
		results.setJudgmentScore(judgmentsScore);
		results.setProgramScore(0);
		results.setSingleAnswer(allSingleAnswer);
		results.setMultipleAnswer(allMultipleAnswer);
		results.setBlankAnswer(allBlankAnswer);
		results.setJudgmentAnswer(allJudgmentAnswer);
		results.setProgramAnswer(null);
		results.setTotalScore(totalScore);
		resultsService.saveEntity(results);
		return "saveAnswer";
	}
	
	@Resource
	private ProgramAnswersService programAnswersService;
	
	/**
	 * 将每道编程题的代码保存进数据库等待教师判卷
	 */
	@Transactional
	@SkipValidation
	public void saveProgram() {
		result = "0";
		this.model = paperService.getPaper(sid);//获取该id的试卷实体
		String subject = model.getSubject();//该试卷的科目
		String enumber = model.getNumber();//该试卷的卷号
		String ename = model.getName();//该试卷的名称
		int score = model.getScore();//该试卷的总分
		HttpSession s = ServletActionContext.getRequest().getSession();
		Student student = (Student) s.getAttribute("student");
		if (student == null || "".equals(student)) {
			return;
		}
		String snumber = student.getNumber();//当前学生的学号
		String sname = student.getName();//当前学生的姓名
		String sclas = student.getClas();//当前学生的班级
		int escore = 0;//每道题的分数
		List<PaperItem> programsList = paperItemService.getProgram(enumber,"program");//根据试卷号和编程类型获取子条目集合
		Iterator<PaperItem> programIt = programsList.iterator();
		while (programIt.hasNext()) {
			PaperItem paperItem = programIt.next();
			escore = paperItem.getScore();//获取每题的分数
		}
		ProgramAnswers pa = programAnswersService.getProgramAnswers(enumber, snumber);//根据试卷号和学号获取编程答案表中的数据
		if (pa == null) {//判断当数据为空的时候
			ProgramAnswers programAnswers = new ProgramAnswers();//insert一条新记录
			programAnswers.setSubject(subject);
			programAnswers.setSnumber(snumber);
			programAnswers.setSname(sname);
			programAnswers.setSclas(sclas);
			programAnswers.setEnumber(enumber);
			programAnswers.setEname(ename);
			programAnswers.setEscore(escore);
			programAnswers.setScore(score);
			programAnswers.setStatus(0);//状态为0代表为批阅
			switch(count) {//判断当前保存的是第几道题
				case 1:
					programAnswers.setFirst(code);
					break;
				case 2:
					programAnswers.setSecond(code);
					break;
				case 3:
					programAnswers.setThird(code);
					break;
				case 4:
					programAnswers.setForth(code);
					break;
				case 5:
					programAnswers.setFifth(code);
					break;
				default:
					break;
			}
			result = "1";//将异步结果定义为1
			programAnswersService.saveEntity(programAnswers);
		} else {//如果不为空的话，进行更新操作
			int id = pa.getId();
			pa.setId(id);
			switch(count) {//判断当前保存的是第几道题
				case 1:
					pa.setFirst(code);
					break;
				case 2:
					pa.setSecond(code);
					break;
				case 3:
					pa.setThird(code);
					break;
				case 4:
					pa.setForth(code);
					break;
				case 5:
					pa.setFifth(code);
					break;
				default:
					break;
			}
			result = "1";
			programAnswersService.saveOrUpdateEntity(pa);
		}
		HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(result);  //将结果返回
	        writer.flush();  
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 学生提交编程试卷
	 * @return
	 */
	@Transactional
	@SkipValidation
	public String finishSave() {
		this.model = paperService.getPaper(sid);
		String subject = model.getSubject();
		String enumber = model.getNumber();
		String ename = model.getName();
		HttpSession s = ServletActionContext.getRequest().getSession();
		Student student = (Student) s.getAttribute("student");
		if (student == null || "".equals(student)) {
			return null;
		}
		String snumber = student.getNumber();
		String sname = student.getName();
		String sclas = student.getClas();
		
		Results result1 = resultsService.isRegisted(enumber, snumber);
		if (result1 != null) {
			inputPage = "/student/editor.jsp";
			return INPUT;
		}
		Results results = new Results();
		results.setSubjcet(subject);
		results.setSnumber(snumber);
		results.setSname(sname);
		results.setSclas(sclas);
		results.setEname(ename);
		results.setEnumber(enumber);
		resultsService.saveEntity(results);//将当前学生信息和试卷记录保存到成绩表中
		return "finishSave";
	}
	
	@SkipValidation
	public String changeStatus() {
		this.model = paperService.getPaper(sid);
		int i = model.getStatus();
		if (i == 0) {
			model.setStatus(1);
			paperService.updateEntity(model);
		} else {
			model.setStatus(0);
			paperService.updateEntity(model);
		}
		return "changeStatus";
	}
	
	@SkipValidation
	public String mchangeStatus() {
		this.model = paperService.getPaper(sid);
		int i = model.getStatus();
		if (i == 0) {
			model.setStatus(1);
			paperService.updateEntity(model);
		} else {
			model.setStatus(0);
			paperService.updateEntity(model);
		}
		return "mchangeStatus";
	}
	
	/**
	 * 保存/更新
	 */
	@SkipValidation
	public String saveOrUpdate(){
		paperService.saveOrUpdate(model);
		inputPage = "/teacher/modifyPaper.jsp";
		return "tofindAllPaper" ;
	}
	
	@Transactional
	public String doAdd() {
		
		String result = "";
		boolean error = true;
		
		//判断单选数量是否足够
		int nsingleCount = singleChoiceService.getQuestionCount(model.getSubject(), model.getDifficult());
		System.out.println("["+model.getSubject()+"]的单选题"+"数量是:"+nsingleCount);
		if (nsingleCount < model.getSingleCount())
        {  
			error=false;
			result="单选题题数:"+model.getSingleCount()+"超过题库单选题总数量:"+nsingleCount;//如果单选题题数不够，返回错误信息
        }
		
		//判断多选数量是否足够
		int nmultipleCount = multipleChoiceService.getQuestionCount(model.getSubject(), model.getDifficult());
		System.out.println("["+model.getSubject()+"]的多选题"+"数量是:"+nmultipleCount);
		if (nmultipleCount < model.getMultipleCount())
        {  
			error=false;
			result="多选题题数:"+model.getMultipleCount()+"超过题库多选题总数量:"+nmultipleCount;
        }
		
		
		//判断填空数量是否足够
		int nblankCount = blankService.getQuestionCount(model.getSubject(), model.getDifficult());
		System.out.println("["+model.getSubject()+"]的填空题"+"数量是:"+nblankCount);
		if (nblankCount < model.getBlankCount())
        {  
			error=false;
			result="填空题题数:"+model.getBlankCount()+"超过题库填空题总数量:"+nblankCount;
        }
		
		//判断判断数量是否足够
		int njudgment = judgmentService.getQuestionCount(model.getSubject(), model.getDifficult());
		System.out.println("["+model.getSubject()+"]的判断题"+"数量是:"+njudgment);
		if (njudgment < model.getJudgmentCount())
        {  
			error=false;
			result="判断题题数:"+model.getJudgmentCount()+"超过题库判断题总数量:"+njudgment;
        }
		
		if (!error) {
			 inputPage = "/teacher/autoAddPaper.jsp";
			 System.out.println(result);
			 addActionError(result);
			 return INPUT;
            //response.getWriter().write("{code:0,message:'"+res+"'}");
		} else {//如果库中题量满足，进行抽取试题操作
			HttpSession s = ServletActionContext.getRequest().getSession();
			Teacher teacher = (Teacher) s.getAttribute("teacher");
			model.setAuthor(teacher.getName());
			paperService.saveEntity(model);//先将试卷的信息保存进数据库中
			
			//随机抽取单选题保存在paperItem中
			List<SingleChoice> singleList = singleChoiceService.extract(model.getSingleCount(), model.getStyle(), model.getDifficult());
			System.out.println("singleList的大小"+singleList.size());
			Iterator<SingleChoice> itSingleChoice = singleList.iterator();
			
			while (itSingleChoice.hasNext()) {
				SingleChoice  singleChoice = itSingleChoice.next();
				PaperItem paperItem=new PaperItem();
				//设置题号
				paperItem.setQnumber(singleChoice.getNumber());
				//卷号
				paperItem.setEnumber(model.getNumber());
	           	//设置题型
	           	paperItem.setType("singleChoice");
	           	//设置该题分值
	           	paperItem.setScore(singleScore);
	           	//保存单选
	           	paperItemService.saveEntity(paperItem);
            }
			
			
			//随机抽取多选题保存在paperItem中
			List<MultipleChoice> multipleList = multipleChoiceService.extract(model.getMultipleCount(), model.getStyle(), model.getDifficult());
			Iterator<MultipleChoice> itMultipleChoice = multipleList.iterator();
			while (itMultipleChoice.hasNext()) {
				MultipleChoice  multipleChoice = itMultipleChoice.next();
				PaperItem paperItem=new PaperItem();
				//设置题号
				paperItem.setQnumber(multipleChoice.getNumber());;
				//卷号
				paperItem.setEnumber(model.getNumber());
	           	//设置题型
	           	paperItem.setType("multipleChoice");
	           	//设置该题分值
	           	paperItem.setScore(multipleScore);
	           	//保存多选
	           	paperItemService.saveEntity(paperItem);
            }
			
			//随机抽取填空题保存在paperItem中
			List<Blank> blankList = blankService.extract(model.getBlankCount(), model.getStyle(), model.getDifficult());
			Iterator<Blank> itBlank = blankList.iterator();
			
			while (itBlank.hasNext()) {
				Blank  blank = itBlank.next();
				PaperItem paperItem=new PaperItem();
				//设置题号
				paperItem.setQnumber(blank.getNumber());;
				//卷号
				paperItem.setEnumber(model.getNumber());
	           	//设置题型
	           	paperItem.setType("blank");
	           	//设置该题分值
	           	paperItem.setScore(blankScore);
	           	//保存填空
	           	paperItemService.saveEntity(paperItem);
            }
			
			//随机抽取判断题保存在paperItem中
			List<Judgment> judgmentList = judgmentService.extract(model.getJudgmentCount(), model.getStyle(), model.getDifficult());
			Iterator<Judgment> itJudgment = judgmentList.iterator();
			while (itJudgment.hasNext()) {
				Judgment  judgment = itJudgment.next();
				PaperItem paperItem=new PaperItem();
				//设置题号
				paperItem.setQnumber(judgment.getNumber());;
				//卷号
				paperItem.setEnumber(model.getNumber());
	           	//设置题型
	           	paperItem.setType("judgment");
	           	//设置该题分值
	           	paperItem.setScore(judgmentScore);
	           	//保存判断
	           	paperItemService.saveEntity(paperItem);
            }
			System.out.println("保存试卷成功");
			
			return SUCCESS;
		}
	}
	
	@Transactional
	@SkipValidation
	public String doAddProgram() {
		inputPage = "/teacher/autoAddProgramPaper.jsp";
		if(!ValidateUtil.isValid(model.getName())){
			addFieldError("name", "名称是必填项!");
		}
		if(model.getProgramCount() <=0 || model.getProgramCount() > 5) {
			addFieldError("programCount", "编程数量大于0小于5!");
		}
		if(model.getScore()<=0) {
			addFieldError("score", "编程分数不能小于0");
		}
		//number是否占用
		boolean b = paperService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "卷号已占用!");
		}
		if(this.hasErrors()){
			return INPUT;
		}
		
		String result = "";
		boolean error = true;
		if (model.getProgramCount() > 5) {
			inputPage = "/teacher/autoAddProgramPaper.jsp";
			addFieldError("programCount", "编程数量不能超过5道!");
			return INPUT;
		}
		
		int nprogramCount = programService.getQuestionCount(model.getSubject(), model.getDifficult());
		System.out.println("["+model.getSubject()+"]的编程题"+"数量是:"+nprogramCount);
		if (nprogramCount < model.getProgramCount())
        {  
			error=false;
			result="编程题题数:"+model.getProgramCount()+"超过题库编程题总数量:"+nprogramCount;
        }
		
		if (!error) {
			 inputPage = "/teacher/autoAddProgramPaper.jsp";
			 System.out.println(result);
			 return INPUT;
           //response.getWriter().write("{code:0,message:'"+res+"'}");
		} else {
			
			HttpSession s = ServletActionContext.getRequest().getSession();
			Teacher teacher = (Teacher) s.getAttribute("teacher");
			model.setAuthor(teacher.getName());
			paperService.saveEntity(model);
			
			//随机抽取编程题保存在paperItem中
			List<Program> programList = programService.extract(model.getProgramCount(), model.getStyle(), model.getDifficult());
			Iterator<Program> itProgram = programList.iterator();
			
			while (itProgram.hasNext()) {
				Program  program = itProgram.next();
				PaperItem paperItem=new PaperItem();
				//设置题号
				paperItem.setQnumber(program.getNumber());;
				//卷号
				paperItem.setEnumber(model.getNumber());
	           	//设置题型
	           	paperItem.setType("program");
	           	//设置该题分值
	           	paperItem.setScore(programScore);
	           	//保存填空
	           	paperItemService.saveEntity(paperItem);
			}
			
			System.out.println("保存编程题成功");
			return "saveProgramSuccess";
		}
	}
	
	public void validate() {
		/*System.out.println("题号="+model.getNumber());
		System.out.println("问题="+model.getQuestion());
		System.out.println("答案="+model.getAnswer());
		System.out.println("科目="+model.getSubject());
		System.out.println("难度="+model.getDifficult());*/
		inputPage = "/teacher/autoAddPaper.jsp";
		if(!ValidateUtil.isValid(model.getName())){
			addFieldError("name", "名称是必填项!");
		}
		if(!ValidateUtil.isValid(model.getSubject())){
			addFieldError("subject", "科目是必填项!");
		}
		if(!ValidateUtil.isValid(model.getStyle())){
			addFieldError("sytle", "类型是必填项!");
		}
		if(model.getSingleCount() < 0) {
			addFieldError("singleCount", "单选数量不能为负数");
		}
		if(model.getMultipleCount() < 0) {
			addFieldError("mutipleCount", "多选数量不能为负数");
		}
		if(model.getBlankCount() < 0) {
			addFieldError("blankCount", "填空数量不能为负数");
		}
		if(model.getJudgmentCount() < 0) {
			addFieldError("judgmentCount", "判断数量不能为负数");
		}
		if(model.getScore()<0) {
			addFieldError("score","分数不能小于0");
		}
		if(this.hasErrors()){
			return ;
		}
		
		//3.number是否占用
		boolean b = paperService.isRegisted(model.getNumber());
		if(b){
			addFieldError("number", "卷号已占用!");
		}
	}
	
}
